import org.junit.Assert;
import org.junit.Test;

import static edu.gvsu.dlunit.DLUnit.*;
import static edu.gvsu.dlunit.DLUnit.readPin;

/**
 * Sample test cases for 16-bit set less than circuit
 * <p/>
 * IMPORTANT:  These test cases do *not* thoroughly test the circuit.  You need to
 * re-name this class and add more tests!
 * <p/>
 * Created by kurmasz on 8/8/16.
 */
public class SampleSlt16BitTest {

  public void verify(long a, long b, boolean signed) {

    if (signed) {
      setPinSigned("InputA", a);
      setPinSigned("InputB", b);
    } else {
      setPinUnsigned("InputA", a);
      setPinUnsigned("InputB", b);
    }

    setPin("Signed", signed);
    run();


    ////////////////////////////////////////
    //
    // Check the correctness of the output
    //
    ///////////////////////////////////////
    String message = String.format("%d < %d (%s): ", a, b, signed ? "signed" : "unsigned");
    Assert.assertEquals("Output " + message, a < b, readPin("LessThan"));
  }

  @Test
  public void zero_zero_signed() {
    verify(0, 0, true);
  }

  @Test
  public void zero_one_signed() {
    verify(0, 1, true);
  }

  @Test
  public void one_zero_signed() {
    verify(1, 0, true);
  }

  @Test
  public void zero_negone_signed() {
    verify(0, -1, true);
  }

  @Test
  public void negone_zero_signed() {
    verify(-1, 0, true);
  }

  @Test
  public void one_negone_signed() {
    verify(1, -1, true);
  }

  @Test
  public void negone_one_signed() {
    verify(-1, 1, true);
  }

  @Test
  public void zero_zero_unsigned() {
    verify(0, 0, false);
  }

  @Test
  public void zero_one_unsigned() {
    verify(0, 1, false);
  }

  @Test
  public void one_zero_unsigned() {
    verify(1, 0, false);
  }


  // Larger scale error catching
  private int[] signedNums = {
	-32768, 32767, -1, 0, 1,
	-30000, 30000,
	452, -827, -64, 64,
	128, 129, 127,
	-128, -129, -127
  };

  private int[] unsignedNums = {
	0, 64, 7248, 128,
	65535, 65534,
	30000, 50000,
	128, 129, 127
  };

  @Test
  public void many_signed_tests() {
	  for(int num_one : signedNums) {
	  	for(int num_two : signedNums) {
			verify(num_one, num_two, true);
		}
	  }
  }

  @Test
  public void many_unsigned_tests() {
	  for(int num_one : unsignedNums) {
	  	for(int num_two : unsignedNums) {
			verify(num_one, num_two, false);
	 	}
	  }
  }

}
