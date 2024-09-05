package test.com.model.alert;

import com.main.model.alert.Alert;
import com.main.model.alert.InfromativeAlert;
import com.main.model.alert.UrgentAlert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class AlertTest {

    @Test
    public void SingleAlertTest() {
        ArrayList<Alert> alertList = new ArrayList<>();
        InfromativeAlert alert = new InfromativeAlert("alert 1", "alert 1");
        alert.apendMessage(alertList);
        Assert.assertTrue(alertList.contains(alert));
    }

    @Test
    public void InformativeAlertsAreInsertedAsFIFOTest() {
        ArrayList<Alert> alertList = new ArrayList<>();
        InfromativeAlert alert1 = new InfromativeAlert("alert 1", "alert 1");
        InfromativeAlert alert2 = new InfromativeAlert("alert 2", "alert 2");
        InfromativeAlert alert3 = new InfromativeAlert("alert 3", "alert 3");

        alert1.apendMessage(alertList);
        alert2.apendMessage(alertList);
        alert3.apendMessage(alertList);

        Assert.assertEquals(alert1, alertList.get(0));
        alertList.remove(0);
        Assert.assertEquals(alert2, alertList.get(0));
        alertList.remove(0);
        Assert.assertEquals(alert3, alertList.get(0));
        alertList.remove(0);
    }

    @Test
    public void UrgentAlertsAreInsertedAsLIFOTest() {
        ArrayList<Alert> alertList = new ArrayList<>();
        UrgentAlert alert1 = new UrgentAlert("alert 1", "alert 1");
        UrgentAlert alert2 = new UrgentAlert("alert 2", "alert 2");
        UrgentAlert alert3 = new UrgentAlert("alert 3", "alert 3");

        alert1.apendMessage(alertList);
        alert2.apendMessage(alertList);
        alert3.apendMessage(alertList);

        Assert.assertEquals(alert3, alertList.get(0));
        alertList.remove(0);
        Assert.assertEquals(alert2, alertList.get(0));
        alertList.remove(0);
        Assert.assertEquals(alert1, alertList.get(0));
        alertList.remove(0);
    }

    @Test
    public void UrgentAlertsAndImportantAlertsTest() {
        ArrayList<Alert> alertList = new ArrayList<>();
        UrgentAlert urgentAlert1 = new UrgentAlert("Ualert 1", "Ualert 1");
        InfromativeAlert informativeAlert1 = new InfromativeAlert("Ialert 1", "Ialert 1");
        UrgentAlert urgentAlert2 = new UrgentAlert("Ualert 2", "Ualert 2");
        InfromativeAlert informativeAlert2 = new InfromativeAlert("alert 2", "alert 2");
        UrgentAlert urgentAlert3 = new UrgentAlert("Ualert 3", "Ualert 3");
        InfromativeAlert informativeAlert3 = new InfromativeAlert("Ialert 3", "Ialert 3");

        urgentAlert1.apendMessage(alertList);
        informativeAlert1.apendMessage(alertList);
        urgentAlert2.apendMessage(alertList);
        informativeAlert2.apendMessage(alertList);
        urgentAlert3.apendMessage(alertList);
        informativeAlert3.apendMessage(alertList);

        Assert.assertEquals(urgentAlert3, alertList.get(0));
        alertList.remove(0);
        Assert.assertEquals(urgentAlert2, alertList.get(0));
        alertList.remove(0);
        Assert.assertEquals(urgentAlert1, alertList.get(0));
        alertList.remove(0);
        Assert.assertEquals(informativeAlert1, alertList.get(0));
        alertList.remove(0);
        Assert.assertEquals(informativeAlert2, alertList.get(0));
        alertList.remove(0);
        Assert.assertEquals(informativeAlert3, alertList.get(0));
        alertList.remove(0);
    }



}
