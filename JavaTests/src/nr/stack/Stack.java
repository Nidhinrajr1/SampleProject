package nr.stack;

import java.util.Scanner;

public class Stack {

		public static void main(String[] args) {

		System.out.println("**************** Welcome to Stack Demonstration ****************");
		System.out.println("");
		System.out.println("1. Start my application(Create an empty stack at backend)");
		System.out.println("2. Start text editing(Add operations to stack)");
		System.out.println("3. Undo the last operation(Perform undo by popping elements from stack)");
		System.out.println("4. Save the document(Empty the stack)");
		System.out.println("Type any number between 1 to 4 to perform the operation associated with each number");
		System.out.println("Type 0 to quit");

		Scanner in = new Scanner(System.in);

		int n = 5;
		n = in.nextInt();
		StackArrays s1 = new StackArrays();
		while (n != 0) {

			switch (n) {
			case 1:
				s1.createStack();
				System.out.println("Stack created");
				break;
			case 2:
				String s = "";
				Scanner inline = new Scanner(System.in);
				s = inline.nextLine();
				if (s1.push(s) == true)
					System.out.println("Performed push for :" + s);
				break;
			case 3:
				String undoString = s1.pop();
				System.out.println("Popped:" + undoString);
				break;
			case 4:
				s1.deleteStack();
				System.out.println("Stack deleted");
				break;
			default:
				System.out.println("in default");
				break;
			}

			n = in.nextInt();

		}

		if (n == 0)
			System.out.println("The file is saved successfully");
		else
			System.out.println("Error in application");

		in.close();
	}

}
