public class Planet{

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static double G = 0.0000000000667;

    public Planet(double xP, double yP, double xV,
              double yV, double m, String img) {

                this.xxPos = xP;
                this.yyPos = yP;
                this.xxVel = xV;
                this.yyVel = yV;
                this.mass = m;
                this.imgFileName = img;

              }


    public Planet(Planet p) {

        p.xxPos = this.xxPos;
        p.yyPos = this.yyPos;
        p.xxVel = this.xxVel;
        p.yyVel = this.yyVel;
        p.mass = this.mass;
        p.imgFileName = this.imgFileName;

    }


    public double calcDistance(Planet p) {
      /** Count dx and dy */
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;

      /** Count distance between two planets */
        double ds = Math.sqrt(dy * dy + dx * dx);

        return ds;
    }

    /** Culculate force exert by one planet */
    public double calcForceExertedBy(Planet p) {

       double r = p.calcDistance(this);
       double force = (G * this.mass * p.mass) / (r * r);

       return force;
    }
    
    /** Culculate force exert by X */
    public double calcForceExertedByX(Planet p) {

      double dx = p.xxPos - this.xxPos;
      double r = p.calcDistance(this);

      return calcForceExertedBy(p) * dx / r;
    }

    /** Culculate force exert by Y */
    public double calcForceExertedByY(Planet p) {

      double dy = p.yyPos - this.yyPos;
      double r = p.calcDistance(this);

      return calcForceExertedBy(p) * dy / r;
    }

    /** Culculate netforce exert by X */
    public double calcNetForceExertedByX(Planet[] ps) {

      double totalX = 0.0;

      for (Planet p : ps) {
        if (p.equals(this)) {
          continue;
        }
        totalX = calcForceExertedByX(p) + totalX;     
      }
      return totalX;
    }

    /** Culculate netforce exert by Y */
    public double calcNetForceExertedByY(Planet[] ps) {

      double totalY = 0.0;

      for (Planet p : ps) {
        if (p.equals(this)) {
          continue;
        }
        totalY = calcForceExertedByY(p) + totalY;
      }
      return totalY;
    }

    /** Update velocity and position of planet with x-force and y-force */
    public void update(double dt, double fX, double fY) {

      double aX = fX / this.mass;
      double aY = fY / this.mass;
      double newXVel = this.xxVel + dt * aX;  
      double newYVel = this.yyVel + dt * aY;

      this.xxPos = this.xxPos + dt * newXVel;
      this.yyPos = this.yyPos + dt * newYVel;

      this.xxVel = newXVel;
      this.yyVel = newYVel;
    }

    public void draw() {

      StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }

}
