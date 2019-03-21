# HW 06 - Interactive GUIs - Due Monday, October 29 at 11:59 PM

Now that we've had a chance to work with GUIs a little bit, lets combine our standard bank account class with a GUI.

:warning: All classes should be in the hw06 package

Create a new Account class. This account class should have a double precision floating point ```balance``` field. You may choose to implement other fields as well, especially for data that needs to be available to multiple methods, but I'll let you choose when and how to define other fields.

The Account class needs to implement two interfaces, the *Runnable* interface and the *ActionListener* interface. Since the Account class is going to have a GUI, let's make the Account class extend the JPanel class.

## Account Class Creator

The first thing we need to define is the Account class Creator. In the past, we have used an argument to our Account creator to specify the initial balance. You can do so if you want, or just make a no-argument creator, and initialize the balance to 0.0.

If we want to pass any arguments into our Superclass creator (JPanel), then we need to invoke super(arglist), but JPanel has a no-argument creator, and Java will invoke that no-argument creator for us implicitly, so we don't need to do anything special.

Next, in the Account Class creator, we can define what an Account panel will look like. First, let's use the BorderLayout layout manager for this panel. (The default layout manager is the FlowLayout manager, wich works, but doesn't provide as much flexibility as BorderLayout.) 

In the NORTH section of the panel, add a JLabel field that contains the current balance of the account. (Note that we will have to update the text displayed whenever the balance changes, so methods which change the balance will need access to this JLabel component.)

In the EAST section of the panel, lets make a sub-panel for action buttons. Our GUI will need three buttons... "Deposit", "Withdraw", and "Exit". Let's lay these out in a vertical column, so we can use the BoxLayout layout manager for our buttons sub-panel. We need to make the Account class itself an actionListener for each of these buttons.

In the CENTER section of the panel, lets make another sub-panel to keep track of the amount to deposit or withdraw. This needs to be a sub-panel because we need two components - a label to tell the user what is here, and an editable amount field so the user can type things in. Since the default layout in a panel is a FlowLayout, and FlowLayout does a good job of managing a label and an editable field, we don't need to do anything special for layout. Add a JLabel description of the field that has the text "Amount:". Then, add a JTextField component. I allowed up to 10 columns for the amount, and initialized it to 0.00. (Note, you might want to use the Java expression ```String.format("%.02f",amount)``` to convert an amount double into characters with two decimal places.)

Finally, the creator can add the creation and display of our GUI as a callback from the event que by invoking the command ```javax.swing.SwingUtilities.invokeLater(this);```

## Account Class Standard Methods

Add a ```deposit``` and ```withdraw``` method to the Account class, both which take a double precision floating point argument of amount to withdraw or deposit. Remeber that since these methods change the balance, these methods will also need to update the text in the current balance field displayed in the NORTH section of the panel.

## Account Class Run Method

We need a run method to get us going. This is the method that will be called from the event loop, once the event loop is running, and it needs to make a JFrame. Note that we will need access to the frame in order to react to the "Exit" button, so we need to make this a field. My code for the run method was as follows:

```
@Override
public void run() {
	frame=new JFrame("Account");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setContentPane(this);
	frame.pack();
	frame.setVisible(true);
}
 ```
 
 ## Account Class actionPerformed Method
 
 Since we implemented the ActionListener interface, we need an ```actionPerformed``` method which takes an ActionEvent reference as an argument, and returns void. When we created our three buttons, "Deposit", "Withdraw", and "Exit"; we defined "this" as the actionListener so that whenever a user pushes one of those buttons, our actionPerformed method would be called. We can use the "getActionCommand" method to get the action command string associated with the button that was pushed, so we can tell which button was pushed. Therefore, at the top level, the code looks something like this...
 
 ```
 @Override
	public void actionPerformed(ActionEvent e) {
		String ac=e.getActionCommand();
		if (ac.equals("Exit")) { // handle Exit button
		} else if (ac.equals("Deposit")) { // handle Deposit button
		} else if (ac.equals("Withdraw")) { // handle Withdraw button
		} else System.out.println("Button " + ac + " press ignored");		
	}
 ```
 
If the "Exit" button is pressed, all you need to do is invoke the ```frame.dispose()``` method. That closes up the GUI, shuts down the event loop, and returns to your code.

If either the "Withdraw" or "Deposit" button is pushed, you need to get the text that the user has typed into the JTextField component using the ```getText()``` method. Then you need to convert that from a character string to a double precision floating point number. I 
did that using ```double amount=Double.parseDouble(amtStr);```. I then use the resulting amount as an argument to the deposit or withdraw methods.

## Account Class main Method

The Account Class main method is now the simplest one we have seen yet. Since we don't need to do anything but create a new Account object, and since we will never actually use that object, we don't even need to keep a reference to that object. (If we do, the compiler will warn us that we never use that reference.)  Therefore, the entire main method looks like:

```
public static void main(String[] args) {
		new Account();
	}
```

## Testing the Account Class

Run the Account Class main method. Try typing values into the amount field and hitting the deposit and withdraw buttons.  What happens when you type an invalid amount into the amount field and hit deposit or withddraw? Note that a failure in a specific event does not stop the event loop!

## Enhanceing the Account GUI

Now that we have a basic Account GUI, there are many many enhancements you can make, such as the following...

1. Add a message are to the South field to convey messages in special occasions.

2. Prevent the user from withdrawing more than his current balance.

3. Make the font size larger so it's easier to read.

4. Center the buttons within the button panel rather than having them right justified

5. Color code the buttons

6. Format the current balance and amount fields using a dollar sign (You will have to remove the dollar sign before invoking toDouble.)

7. Print transactions, as they occur, to the console.

8. Prevent terrible messages from showing up on the console when there is an invalid amount by using a try/catch around the toDouble invocation.

9. Add a Password field which is initialized from a creator argument. Then, put up a dialog that requires the user to type in the password before displaying the account.

## Using Eclipse to commit your results

This should be getting familiar. Switch to the Git perspective, select "hw06", then click the "Refresh" icon and stage all your changes. Select the "Git Staging" tab, and make sure all your modified files are in the "Staged Changes" window. Type a commit message in the "Commit Message" sub-window. Then "Commit and Push...", get through the commit wizard and you will get a pop-up that tells you that the commit was succesful. Now, click on the "Git Reflog" tab, select the final commit, and right click to copy the commit ID. This is the commit hash code that can now be pasted into myCourses.

## Grading Criteria

This homework is worth 10 points. If your code does not compile, you will get an 8 point deduction. If your code gets a compiler warning, you will get a 2 point deduction. The CA's will run your hw06.Account main function. If the GUI does not do everything requested with correct results (we're only grading the basic function - not the enhancements), you will get up to a 5 point deduction, depending on the severity of the problem. No points will be added or subtracted for extra enhancements *UNLESS* these enhancements inhibit the basic function of the GUI.
