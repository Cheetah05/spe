package uk.me.graphe.client;

import java.util.Random;

import junit.framework.Assert;
import junit.framework.TestCase;
import uk.me.graphe.shared.VertexDirection;

public class EdgeDrawableTest extends TestCase {
    static int[] sTestValues = new int[] { 0, -10, 7, Integer.MAX_VALUE,
            Integer.MIN_VALUE };
    static Random sRand = new Random();

    /**
     * tests that start x works as expected
     */
    public void testStartX() {
        for (int i : sTestValues) {
            EdgeDrawable ed = new EdgeDrawable(i, sRand.nextInt(), sRand
                    .nextInt(), sRand.nextInt(), 0);
            Assert.assertEquals(i, ed.getStartX());
        }
    }

    /**
     * tests that start y works as expected
     */
    public void testStartY() {
        for (int i : sTestValues) {
            EdgeDrawable ed = new EdgeDrawable(sRand.nextInt(), i, sRand
                    .nextInt(), sRand.nextInt(), 0);
            Assert.assertEquals(i, ed.getStartY());
        }
    }

    /**
     * tests that end x works as expected
     */
    public void testEndX() {
        for (int i : sTestValues) {
            EdgeDrawable ed = new EdgeDrawable(sRand.nextInt(),
                    sRand.nextInt(), i, sRand.nextInt(), 0);
            Assert.assertEquals(i, ed.getEndX());
        }
    }

    /**
     * tests that end y works as expected
     */
    public void testEndY() {
        for (int i : sTestValues) {
            EdgeDrawable ed = new EdgeDrawable(sRand.nextInt(),
                    sRand.nextInt(), sRand.nextInt(), i, 0);
            Assert.assertEquals(i, ed.getEndY());
        }
    }

    /**
     * tests the use of needsarrow in an edge drawable
     */
    public void testArrow() {
        EdgeDrawable e = new EdgeDrawable(sRand.nextInt(), sRand.nextInt(),
                sRand.nextInt(), sRand.nextInt(), 0);
        Assert.assertEquals(false, e.needsFromToArrow());
        Assert.assertEquals(false, e.needsToFromArrow());
        e = new EdgeDrawable(sRand.nextInt(), sRand.nextInt(), sRand.nextInt(),
                sRand.nextInt(), 0, VertexDirection.both);
        Assert.assertEquals(false, e.needsFromToArrow());
        Assert.assertEquals(false, e.needsToFromArrow());
        e = new EdgeDrawable(sRand.nextInt(), sRand.nextInt(), sRand.nextInt(),
                sRand.nextInt(), 0, VertexDirection.fromTo);
        Assert.assertEquals(true, e.needsFromToArrow());
        Assert.assertEquals(false, e.needsToFromArrow());
        e = new EdgeDrawable(sRand.nextInt(), sRand.nextInt(), sRand.nextInt(),
                sRand.nextInt(), 0, VertexDirection.toFrom);
        Assert.assertEquals(false, e.needsFromToArrow());
        Assert.assertEquals(true, e.needsToFromArrow());
    }
    
    public void testWeight() {
        EdgeDrawable ed = new EdgeDrawable(1, 2, 3, 4, 7);
        Assert.assertEquals(7, ed.getWeight());
    }
}
