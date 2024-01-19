package part02;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import java.util.stream.DoubleStream;

/**
 * This class demonstrates how Stream operations handle situations where there
 * is no value to return. For example, if you try to get the maximum value of an
 * empty stream, there is no maximum value to return. In this case, the
 * operation returns a special value called OptionalDouble, which may or may not
 * contain a value.
 */
public class OptionalValues {

    /**
     * Returns the average of integers in the specified IntStream. If the stream is
     * empty, returns 0.0.
     *
     * @param stream the IntStream to average
     * @return the average of the numbers in the stream or 0 if the stream is empty
     */
    public double average(IntStream stream) {
    	
    	OptionalDouble avg = stream.average();
    	
    	if(avg.isPresent()) {
    		return avg.getAsDouble();
    	} else {
    		return 0;
    	}
    	
    	
        /*
         * See the `average()` method in the documentation of IntStream. Note that the
         * stream may be empty, so there may not be an average value. That is why the
         * method returns a special type called OptionalDouble.
         *
         * You can check if the OptionalDouble is empty by calling the `isPresent()`
         * method. Another approach is to use the `orElse()` method, which returns the
         * actual value if it is present, or an alternative value if the OptionalDouble
         * is empty.
         */

    }

    /**
     * Returns the maximum value of integers in the specified IntStream. If the
     * stream is empty, returns 0.
     *
     * @param stream the IntStream to get the maximum value from
     * @return the maximum value in the stream or 0 if the stream is empty
     */
    public double maximum(IntStream stream) {
        OptionalDouble max = stream.asDoubleStream()
        								.max();
        
    	if(max.isPresent()) {
    		return max.getAsDouble();
    	} else {
    		return 0;
    	}
        		
       
    }
}
