/**
 * 
 * def cons(a, b): def pair(f): return f(a, b) return pair

 *
 */
import java.util.function.BiFunction;
import java.util.function.Function;
public class Pairs
{

	
	public static <T,U,R> Function<BiFunction<T,U,R>,R> cons(T a, U b) {
	    Function<BiFunction<T,U,R>,R> pair = f -> f.apply(a,b);
	    return pair;
	  }
	
	
	
	public static void main(String[] args)
	{
		Function<BiFunction<Integer, Integer, Object>, Object> f = (Function<BiFunction<Integer, Integer, Object>, Object>) cons(8,10);
		
	}
	
}