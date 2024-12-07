package subscriptions;

import io.github.eealba.payper.core.PayperAuthenticator;
import io.github.eealba.payper.subscriptions.v1.api.Subscriptions;
import io.github.eealba.payper.subscriptions.v1.model.BillingCycle;

import java.util.List;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        setPaypalProperties();
        listPlan();

    }

    private static void setPaypalProperties() {
        Scanner scanner = new Scanner(System.in);
        //Prompt user to enter credentials
        System.out.println("Enter your client id: ");
        String clientId = scanner.nextLine();

        System.out.println("Enter your client secret: ");
        String clientSecret = scanner.nextLine();

        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_BASE_URL, "https://api-m.sandbox.paypal.com");
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_ID, clientId);
        System.setProperty(PayperAuthenticator.PayperAuthenticators.PAYPAL_CLIENT_SECRET, clientSecret);
    }



    private static void listPlan() {
        var planCollection = Subscriptions.create().billingPlans().list().retrieve().toEntity();
        System.out.println(planCollection.totalItems());

        planCollection.plans().forEach(plan -> {
            System.out.println("Description: " + plan.description());
            System.out.println("id: " + plan.id());
            System.out.println("name: " + plan.name());
            System.out.println("createTime: " + plan.createTime());
            List<BillingCycle> cycles = plan.billingCycles();
            System.out.println(cycles);

        });

    }

}
