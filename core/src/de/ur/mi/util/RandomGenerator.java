package de.ur.mi.util;

import de.ur.mi.graphics.Color;

/**
 * This class implements a simple random number generator that allows clients to
 * generate pseudorandom integers, doubles, booleans, and colors. To use it, the
 * first step is to declare an instance variable to hold the random generator as
 * follows:
 *
 * <p>
 * <font face="courier" size=-1><b>&nbsp;&nbsp;&nbsp;private RandomGenerator
 * rgen = RandomGenerator.getInstance();</b></font>
 * <p>
 * When you then need to generate a random value, you call the appropriate
 * method on the <font face="courier" size=-1><b>rgen</b></font> variable.
 *
 * <p>
 * The <font face="courier" size=-1><b>RandomGenerator</b></font> class is
 * actually implemented as an extension to the <font face="courier"
 * size=-1><b>Random</b></font> class in <font face="courier"
 * size=-1><b>java.util</b></font>. The new version has the following advantages
 * over the original:
 *
 * <p>
 * <ul>
 * <li>The name of the class emphasizes that the object is a random
 * <u>generator</u> rather than a random value.
 * <p>
 * <li>The class includes overloaded versions of <font face="courier"
 * size=-1><b>nextInt</b></font> and <font face="courier"
 * size=-1><b>nextDouble</b></font> to simplify choosing numbers in a specific
 * range.
 * <p>
 * <li>The method <font face="courier" size=-1><b>nextBoolean</b></font> is
 * overloaded to allow the specification of a probability.
 * <p>
 * <li>The class includes a method <font face="courier"
 * size=-1><b>nextColor</b></font> that generates a random opaque color.
 * <p>
 * </ul>
 * <p>
 */
public class RandomGenerator {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static RandomGenerator instance;

    /**
     * Creates a new random generator initialized to an unpredictable starting
     * point. In almost all programs, you want to call this method <u>once</u>
     * to produce a single generator object, which you then use each time you
     * need to generate a random value. If you create several random generators
     * in succession, they will typically generate the same sequence of values.
     *
     * <p>
     * During debugging, it is often useful to set the internal seed for the
     * random generator explicitly so that it always returns the same sequence.
     * To do so, you need to invoke the <a href=
     * "http://com/j2se/1.4.2/docs/api/java/util/Random.html#setSeed(int)"><font
     * face="courier" size=-1><b>setSeed</b></font></a> method.
     *
     * </dd><br>
     * &nbsp;<br>
     * <dd>
     * <dd>
     * <table border=0 cellpadding=0 cellspacing=3>
     * <tr>
     * <td width=100 valign=top><b>Usage:</b><font face="courier"
     * size=-1><b>&nbsp;</b></font></td>
     * <td><font face="courier" size=-1><b>RandomGenerator rgen = new
     * RandomGenerator();</b></font>&nbsp;</td>
     * </tr>
     * </table>
     * &nbsp;<br>
     * </dd>
     */
    public RandomGenerator() {
        super();
    }

    /**
     * This method returns a <font face="courier"
     * size=-1><b>RandomGenerator</b></font> instance that can be shared among
     * several classes.
     *
     *  RandomGenerator rgen = RandomGenerator.getInstance();
     * @return A shared RandomGenerator object
     */
    public static RandomGenerator getInstance() {
        if (instance == null) {
            instance = new RandomGenerator();
        }
        return instance;
    }

    /**
     * Returns a random <font face="courier" size=-1><b>boolean</b></font> value
     * with the specified probability. You can use this method to simulate an
     * event that occurs with a particular probability. For example, you could
     * simulate the result of tossing a coin like this:
     *
     * <p>
     * <font face="courier" size=-1><b>&nbsp;&nbsp;&nbsp;String coinFlip =
     * rgen.nextBoolean(0.5) ? "HEADS" : "TAILS";</b></font>
     *
     *  if (rgen.nextBoolean(p)) . . .
     * @param p
     *            A value between 0 (impossible) and 1 (certain) indicating the
     *            probability
     * @return The value true with probability p
     */
    public boolean nextBoolean(double p) {
        return Math.random()>0.5;
    }

    /**
     * Returns a random opaque {@link Color Color} whose
     * components are chosen uniformly in the 0-255 range.
     *
     *  Color color = rgen.newColor()
     * @return A random opaque Color
     */
    public Color nextColor() {

        return new Color(
                (int)(Math.random() * 255),
                (int)(Math.random() * 255),
                (int)(Math.random() * 255));
    }

    /**
     * Returns the next random real number in the specified range. The resulting
     * value is always at least <font face="courier" size=-1><b>low</b></font>
     * but always strictly less than <font face="courier"
     * size=-1><b>high</b></font>. You can use this method to generate
     * continuous random values. For example, you can set the variables <font
     * face="courier" size=-1><b>x</b></font> and <font face="courier"
     * size=-1><b>y</b></font> to specify a random point inside the unit square
     * as follows:
     *
     * <p>
     * <font face="courier" size=-1><b>&nbsp;&nbsp;&nbsp;double x =
     * rgen.nextDouble(0.0, 1.0); <br>
     * &nbsp;&nbsp;&nbsp;double y = rgen.nextDouble(0.0, 1.0);</b></font> </dd><br>
     * &nbsp;<br>
     *
     *  double d = rgen.nextDouble(low, high)
     * @param low
     *            The low end of the range
     * @param high
     *            The high end of the range
     * @return A random double value d in the range low ≤ d < high
     */
    public double nextDouble(double low, double high) {
        return Math.random()*(high-low)+low;
    }

    /**
     * Returns the next random integer in the specified range. For example, you
     * can generate the roll of a six-sided die by calling
     *
     * <p>
     * <font face="courier" size=-1><b>&nbsp;&nbsp;&nbsp;rgen.nextInt(1,
     * 6);</b></font>
     * <p>
     * or a random decimal digit by calling
     *
     * <p>
     * <font face="courier" size=-1><b>&nbsp;&nbsp;&nbsp;rgen.nextInt(0,
     * 9);</b></font> </dd><br>
     * &nbsp;<br>
     *
     *  int k = rgen.nextInt(low, high)
     * @param low
     *            The low end of the range
     * @param high
     *            The high end of the range
     * @return The next random int between low and high, inclusive
     */
    public int nextInt(int low, int high) {
        return (int)Math.round(Math.random()*(high-low)+low);
    }

}