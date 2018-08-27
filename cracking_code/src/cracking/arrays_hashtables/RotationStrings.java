package cracking.arrays_hashtables;

class RotationStrings {
	  public static void main(String[] args) {
	    RotationStrings obj = new RotationStrings();
	    System.out.println(obj.isRotation("walkthisway", "thisthisway"));

	     System.out.println(obj.isRotation("walkthisway", "thiswaywalk"));
	  }
	  
	  boolean isRotation(String s1, String s2) {
	    if (s1 == null || s2 == null || s1.length() != s2.length()) {
	      return false;
	    } 
	    String tmpString = s1 + s1;
	    if (tmpString.contains(s2))
	      return true;
	    else
	      return false;
	  }
	}