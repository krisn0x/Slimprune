package com.chris.slimprune;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumApplication;
import com.leanplum.LeanplumDeviceIdMode;
import com.leanplum.Var;
import com.leanplum.annotations.Parser;
import com.leanplum.custommessagetemplates.MessageTemplates;


public class ApplicationClass extends LeanplumApplication {


    // Define the file variable with Var.define.
    public static Var<String> drawableColor = Var.define("drawableColor", "#246697");
    public static Var<String> bgColor = Var.define("bgColor", "#2b628f");
//    public static Var<String> testVar = Var.define("testVar", "PENKE IDVAM");


    @Override
    public void onCreate() {
        super.onCreate();
        Leanplum.setApplicationContext(this);

        // Insert your API keys here.
        if (BuildConfig.DEBUG)
            Leanplum.setAppIdForDevelopmentMode("app_lQx9cGPK5hvHKbggkLs9TkrWikNnGWc2Sr6kXWGV3qg", "dev_XlebSpc6w2Hkkqk6OZUXQEHQXKcoJb2Ex8qDN5SvgQY");
//            Leanplum.setAppIdForProductionMode("app_lQx9cGPK5hvHKbggkLs9TkrWikNnGWc2Sr6kXWGV3qg", "prod_Gzt092p0INhbcj7zUz3h7G4QAqUA6wveoe6yXyWTYgg");
        else
            Leanplum.setAppIdForProductionMode("app_lQx9cGPK5hvHKbggkLs9TkrWikNnGWc2Sr6kXWGV3qg", "prod_Gzt092p0INhbcj7zUz3h7G4QAqUA6wveoe6yXyWTYgg");


        // Optional: Tracks all screens in your app as states in Leanplum.
        // Leanplum.trackAllAppScreens();

        // This will only run once per session, even if the activity is restarted.

        MessageTemplates.register(getApplicationContext());

        LeanplumTestVar leanplumTestVar = new LeanplumTestVar();
        leanplumTestVar.getAndroidOnboardingConfig();

//        Parser.parseVariablesForClasses(LeanplumTestVar.class);
        Leanplum.start(this);

    }
}