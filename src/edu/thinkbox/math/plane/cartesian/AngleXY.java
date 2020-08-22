package edu.thinkbox.math.plane.cartesian;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import edu.thinkbox.math.matrix.Matrix;

public class AngleXY extends XYObject{
        private Arc  angle;
        private Text angleValue;
        private Matrix previous = Matrix.createColumnMatrix( 2 );

        public AngleXY( XYPlane plane ){
           super( plane );
           angle = new Arc();
           angleValue = new Text();
           createAngle();
        }

        public AngleXY( double x, double y, XYPlane plane ){
           this( plane );
           setPlaneCoordinates( x, y );
        }

        public void setColor( Color color ){}

        public void highlight(){
            angle.setStroke( RED );
            angle.setStrokeWidth( getHighlightSize() );
        }

        public void unhighlight(){
            angle.setStroke( GREEN );
            angle.setStrokeWidth( getSize() );
        }

        private void updateAngleValue(){
             Double x = 40 * Math.cos( plane.getDirection( getCoordinates() ) / 2.0 );
             Double y = 40 * Math.sin( plane.getDirection( getCoordinates() ) / 2.0 );

             if( plane.getQuadrant( getCoordinates() ) == 1 ){
                   angleValue.setX( plane.getCenterX() + ( x - 10 ) );
                   angleValue.setY( plane.getCenterY() - y );
             } else if( plane.getQuadrant( getCoordinates() ) == 2 ){
                   angleValue.setX( plane.getCenterX() + ( x - 20 ) );
                   angleValue.setY( plane.getCenterY() - y );
             } else if( plane.getQuadrant( getCoordinates() ) == 3 ){
                   angleValue.setX( plane.getCenterX() + ( x - 25 ) );
                   angleValue.setY( plane.getCenterY() - y );
             } else if( plane.getQuadrant( getCoordinates() ) == 4 ){
                   angleValue.setX( plane.getCenterX() + ( x - 30 ) );
                   angleValue.setY( plane.getCenterY() - y );
             }

             angleValue.setText( String.format( "%2.2f", plane.toDegree( plane.getDirection( getCoordinates() ) ) ) );
        }

        public void setPlaneCoordinates( double x, double y){
             previous.copyEntries( getCoordinates() );
             super.setPlaneCoordinates( x, y );
             updateAngle();
        }

        public void updateAngle(){
            double updatedAngle = plane.toDegree( plane.getDirection( getCoordinates() ) );
            angle.setLength( updatedAngle );
            updateAngleValue();
        }

        public void createAngle(){
            angle.setCenterX( plane.getCenterX() );
            angle.setCenterY( plane.getCenterY() );
            angle.setRadiusX( 25.0f );
            angle.setRadiusY( 25.0f );
            angle.setStartAngle( 0.0f);
            angle.setType( ArcType.ROUND );
            angle.setFill( null );
            angle.setStroke( GREEN );
            angle.setStrokeWidth( getSize() );
            updateAngle();
            getChildren().add( angle );
            getChildren().add( angleValue );
        }
}
