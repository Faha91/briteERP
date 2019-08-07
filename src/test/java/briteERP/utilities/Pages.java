

package briteERP.utilities;
import briteERP.pages.CrmPage;
import briteERP.pages.LoginPage;

public class Pages {

    private CrmPage crmPage;
    private LoginPage loginPage;



    public CrmPage crmpage(){
        if( crmPage ==null) {
            crmPage = new CrmPage();
        }
        return crmPage;
    }

    public LoginPage loginPage(){
        if (loginPage == null){
            loginPage = new LoginPage();
        }
        return loginPage;
    }
}
