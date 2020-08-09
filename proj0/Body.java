
public class Body {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	
	public Body(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	
	public Body(Body b) {
		b.xxPos = xxPos;
		b.yyPos = yyPos;
		b.xxVel = xxVel;
		b.yyVel = yyVel;
		b.mass = mass;
		b.imgFileName = imgFileName;
	}
	
	public double calcDistance(Body b) {
		double dx = this.xxPos - b.xxPos;
		double dy = this.yyPos - b.yyPos;
		return Math.sqrt(dx*dx + dy*dy);
		
	}
	public double calcForceExertedBy(Body b) {
		return 6.7E-11*mass*b.mass/(calcDistance(b)*calcDistance(b));
	}
	public double calcForceExertedByX(Body b) {
		return calcForceExertedBy(b)*(b.xxPos-xxPos)/calcDistance(b);
	}
	public double calcForceExertedByY(Body b) {
		return calcForceExertedBy(b)*(b.yyPos-yyPos)/calcDistance(b);
	}
	
	public double calcNetForceExertedByX(Body[] bs) {
		double netForceX = 0;
		for (int i=0; i<bs.length; i++) {
			if (this.equals(bs[i])) continue;
			else {
				netForceX += this.calcForceExertedByX(bs[i]);
			}
		}
		return netForceX;
	}
	public double calcNetForceExertedByY(Body[] bs) {
		double netForceY = 0;
		for (int i=0; i<bs.length; i++) {
			if (this.equals(bs[i])) continue;
			else {
				netForceY += this.calcForceExertedByY(bs[i]);
			}
		}
		return netForceY;
	}
	public void update(double dt, double xForce, double yForce) {
		double xAcc = xForce/mass;
		double yAcc = yForce/mass;
		xxVel = xxVel + dt*xAcc;
		yyVel = yyVel + dt*yAcc;
		xxPos = xxPos + dt*xxVel;
		yyPos = yyPos + dt*yyVel;
	}
	
	public void draw(){
		StdDraw.enableDoubleBuffering();
		String imageToDraw = "./images/"+this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, imageToDraw);
		StdDraw.show();
	}
}
