public class NBody {

    /** Read radius of the universe from file */
    public static double readRadius(String filename) {
        In in = new In(filename);
        in.readDouble();

        return in.readDouble();
    }

    /** Read planets from file */
    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        Planet[] ps = new Planet[N];
        in.readDouble();

        for (int i = 0; i < N; i = i + 1) {
            ps[i] = new Planet(in.readDouble(), in.readDouble(),
             in.readDouble(), in.readDouble(), in.readDouble(), 
             in.readString());  
        }
        return ps;
    }

    public static void main(String[] args) {
        
        /** Read commend from args */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        double radius = readRadius(args[2]);
        Planet[] ps = readPlanets(args[2]); 

        /** Set scale and background. */
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0.0, 0.0, "images/starfield.jpg");
        

        /** Draw all planets in the array. */
        for (Planet p : ps) {
            p.draw();
        }

        /** This is a graphics technique to prevent flickering in the animation. */
        StdDraw.enableDoubleBuffering();

        /** Set time variable */
        double time = 0;
        while (time < T) {

            double[] xForces = new double[ps.length];
            double[] yForces = new double[ps.length];

            int i = 0;

            /** Calculate the net x and y forces for each planet. */
            for (Planet p : ps) {
                xForces[i] = p.calcNetForceExertedByX(ps);
                yForces[i] = p.calcNetForceExertedByY(ps);
                i += 1;
            }

            i = 0;

            /** Call update on each of the planets. */
            for (Planet p : ps) {
                p.update(dt, xForces[i], yForces[i]);
                i += 1;
            }

            /** Refresh background and planet image. */
            StdDraw.picture(0.0, 0.0, "images/starfield.jpg");
            for (Planet p : ps) {
                p.draw();
            }

            /** Shows the drawing to the screen, and waits 2000 milliseconds. */
            StdDraw.show();
            StdDraw.pause(10);

            /** Increase time variable by dt. */
            time += dt;
        }

        /** Printing the Universe */
        StdOut.printf("%d\n", ps.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < ps.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            ps[i].xxPos, ps[i].yyPos, ps[i].xxVel,
            ps[i].yyVel, ps[i].mass, ps[i].imgFileName);   
        }
    }
}