public class NBody {
	
	public static double readRadius(String str) {
		In in = new In(str);
		int numOfPlanets = in.readInt();
		double radOfUniver = in.readDouble();
		return radOfUniver;
	}
	
	public static Body[] readBodies(String str) {
		In in = new In(str);
		int numOfPlanets = in.readInt();
		double radOfUniver = in.readDouble();
		
		Body allBody[] = new Body[numOfPlanets];
		
		double allData[][] = new double[numOfPlanets][5];
		String allName[] = new String[numOfPlanets];
				
		for (int i = 0; i<numOfPlanets; i++) {
			for (int j = 0; j<5; j++) {
				allData[i][j] = in.readDouble();
			}
			allName[i] = in.readString();
			allBody[i] = new Body(allData[i][0],allData[i][1],allData[i][2],allData[i][3],allData[i][4],allName[i]);
		}

		return allBody;
	}
	public static void main(String[] args) {
		double T = 157788000.0;
		double dt = 25000.0;
		String fileName = "./data/planets.txt";
		int numOfPlanets = 5;
		double radUniver = NBody.readRadius(fileName);
		Body[] allBody = NBody.readBodies(fileName);
		
		
		
		String imageToDraw = "./images/starfield.jpg";
		Draw.draw(radUniver, imageToDraw);
		for (int i =0; i<allBody.length; i++){
			allBody[i].draw();
		}
		
		//start to work 
		StdDraw.enableDoubleBuffering();
		for (double time = 0; time<T; time+=dt){
			double[] xForce = new double[numOfPlanets];
			double[] yForce = new double[numOfPlanets];
			for (int i=0; i<allBody.length; i++){
				xForce[i] = allBody[i].calcNetForceExertedByX(allBody);
				yForce[i] = allBody[i].calcNetForceExertedByY(allBody);
			}
			for (int i=0; i<allBody.length; i++){
				allBody[i].update(dt,xForce[i],yForce[i]);
			}
			Draw.draw(radUniver, imageToDraw);
			for (int i =0; i<allBody.length; i++){
				StdDraw.enableDoubleBuffering();
				allBody[i].draw();
				
			}
		}
		
		StdOut.printf("%d\n", allBody.length);
		StdOut.printf("%.2e\n", radUniver);
		for (int i = 0; i < allBody.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  allBody[i].xxPos, allBody[i].yyPos, allBody[i].xxVel,
                  allBody[i].yyVel, allBody[i].mass, allBody[i].imgFileName);   
		}
	}
}