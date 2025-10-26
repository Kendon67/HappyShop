package ci553.happyshop.client.customer;

// Build card object to store the details required
public class CustomerCard {
    CustomerView custView;

    protected String cardholderName = "";
    protected String cardNumber = "";
    protected String expiryDate = "";
    protected String cvv = "";


public void recieveDetails(String cardholderName, String cardNumber, String expiryDate, String cvv){
    this.cardholderName = cardholderName;
    this.cardNumber = cardNumber;
    this.expiryDate = expiryDate;
    this.cvv = cvv;

    System.out.println(validate());
}

    public boolean validate() {
        if (cardholderName != null
                && cardNumber != null && cardNumber.length() > 15 && cardNumber.length() < 20 && luhnCheck(cardNumber)
                && expiryDate != null
                && cvv != null && cvv.length() > 2 && cvv.length() < 5) {
            return true;
        } else {
            return false;
        }
    }

    // use luhn check to check if the card number is valid in a real world scenario
    protected boolean luhnCheck(String cardNumber){
            int sum = 0;
            boolean secondDigit = false;
            // start with rightmost digit and move left
            for(int i=cardNumber.length()-1; i>=0; i--){
                int num = cardNumber.charAt(i) - '0';
                // double every second digit
                if (secondDigit){
                    num *= 2;
                }
                sum += num / 10;
                sum += num % 10;

                secondDigit = !secondDigit;
                }
            // if sum divided by 10 == 0 then pass the check
            if  (sum % 10 == 0){
                return true;
            }
            else{return false;}
        }
}

