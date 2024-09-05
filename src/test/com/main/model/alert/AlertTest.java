package com.main.model.alert;


import com.model.alert.Alert;
import com.model.alert.InformativeAlert;
import com.model.alert.UrgentAlert;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;

public class AlertTest {

    @Test
    public void SingleAlertTest() {
        ArrayList<Alert> alertList = new ArrayList<>();
        InformativeAlert alert = new InformativeAlert("alert 1", "alert 1");
        alert.appendAlert(alertList);
        Assert.assertTrue(alertList.contains(alert));
    }

    @Test
    public void InformativeAlertsAreInsertedAsFIFOTest() {
        ArrayList<Alert> alertList = new ArrayList<>();
        InformativeAlert alert1 = new InformativeAlert("alert 1", "alert 1");
        InformativeAlert alert2 = new InformativeAlert("alert 2", "alert 2");
        InformativeAlert alert3 = new InformativeAlert("alert 3", "alert 3");

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
        UrgentAlert urgentAlert1 = new UrgentAlert("Urgent alert 1", "Urgent alert 1");
        InformativeAlert informativeAlert1 = new InformativeAlert("Informative alert 1", "Informative alert 1");
        UrgentAlert urgentAlert2 = new UrgentAlert("Urgent alert 2", "Urgent alert 2");
        InformativeAlert informativeAlert2 = new InformativeAlert("Informative alert 2", "Informative alert 2");
        UrgentAlert urgentAlert3 = new UrgentAlert("Urgent alert 3", "Urgent alert 3");
        InformativeAlert informativeAlert3 = new InformativeAlert("Informative alert 3", "Informative alert 3");

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
        InformativeAlert alert = new InformativeAlert("alert 1", "alert 1");

        Assert.assertFalse(alert.isRead());
        alert.markAsRead();
        Assert.assertTrue(alert.isRead());
    }
}
