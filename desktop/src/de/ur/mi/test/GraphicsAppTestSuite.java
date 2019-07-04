package de.ur.mi.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  GraphicsObjectTest.class,
  LineTest.class,
  RectTest.class,
  EllipseTest.class,
  LabelTest.class,
  CompoundTest.class
})

public class GraphicsAppTestSuite {

}