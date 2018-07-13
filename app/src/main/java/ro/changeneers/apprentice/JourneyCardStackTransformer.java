package ro.changeneers.apprentice;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

public class JourneyCardStackTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(@NonNull View page, float position) {

        if(position>=0){
            page.setScaleX(0.8f - 0.02f*position);
            page.setScaleY(0.8f);

            page.setTranslationX(-page.getWidth()*position);
            page.setTranslationY(30*position);
        }
    }
}
