import java.util.*;
import java.io.*;

public class Simulation_Censoring  {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new FileReader ("censor.in"));
        PrintWriter out = new PrintWriter (new FileWriter ("censor.out"));

		StringBuilder answer = new StringBuilder ("");
		String a = in.readLine();
		String censor = in.readLine();

		for (int i = 0; i < a.length(); i ++){
			answer.append(a.charAt(i));
			if (answer.length() >= censor.length()){
				if (answer.substring(answer.length()-censor.length(),answer.length()).equals(censor)){
					answer.delete (answer.length()-censor.length(),answer.length());
				}
			}
		}

		out.println (answer);
		out.close ();
   }
}