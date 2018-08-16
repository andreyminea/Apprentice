package ro.changeneers.apprentice.activities;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;
import ro.changeneers.apprentice.R;

public class AboutUsActivity extends NavDrawer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Element adsElement = new Element();
        adsElement.setTitle("Advertise with us");

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setDescription("Salutare!\n" +
                        "Suntem echipa Apprentice, formată din 8 studenți in cadrul Universității Politehnica din București şi facem parte din Academia Changeneers, organizată  de către Samsung şi Impact Hub Bucharest. \n" +
                        "Aplicaţia Apprentice are scop educaţional şi reprezintă soluţia tehnologică propusă de noi, prin care venim în ajutorul elevilor de liceu ce doresc să îşi dezvolte un nou skill, dar nu ştiu cum să înceapă sau nu dispun de informaţiile şi resursele necesare. Sperăm că aplicaţia îşi îndeplineşte scopul şi îţi este de ajutor!")
                .addGroup("Connect with us")
                .addEmail("echipaapprentice@gmail.com")
                .create();
        setContentView(aboutPage);
    }

    @Override
    protected int getNavigationItemID() {
        return 0;
    }
}


