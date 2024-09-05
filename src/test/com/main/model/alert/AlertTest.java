package com.main.model.alert;


import com.model.alert.Alert;
import com.model.alert.InfromativeAlert;
import com.model.alert.UrgentAlert;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;

public class AlertTest {

    @Test
    public void SingleAlertTest() {
        ArrayList<Alert> alertList = new ArrayList<>();
        InfromativeAlert alert = new InfromativeAlert("alert 1", "alert 1");
        alert.appendAlert(alertList);
        Assert.assertTrue(alertList.contains(alert));
    }

    @Test
    public void InformativeAlertsAreInsertedAsFIFOTest() {
        ArrayList<Alert> alertList = new ArrayList<>();
        InfromativeAlert alert1 = new InfromativeAlert("alert 1", "alert 1");
        InfromativeAlert alert2 = new InfromativeAlert("alert 2", "alert 2");
        InfromativeAlert alert3 = new InfromativeAlert("alert 3", "alert 3");

        alert1.appendAlert(alertList);
        alert2.appendAlert(alertList);
        alert3.appendAlert(alertList);

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

        alert1.appendAlert(alertList);
        alert2.appendAlert(alertList);
        alert3.appendAlert(alertList);

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

        urgentAlert1.appendAlert(alertList);
        informativeAlert1.appendAlert(alertList);
        urgentAlert2.appendAlert(alertList);
        informativeAlert2.appendAlert(alertList);
        urgentAlert3.appendAlert(alertList);
        informativeAlert3.appendAlert(alertList);

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

    @Test
    public void AlertReadTest() {
        InfromativeAlert alert = new InfromativeAlert("alert 1", "alert 1");

        Assert.assertFalse(alert.isRead());
        alert.markAsRead();
        Assert.assertTrue(alert.isRead());
    }
}
