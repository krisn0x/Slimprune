package com.chris.slimprune

import com.leanplum.Var
import com.leanplum.annotations.Variable

class LeanplumTestVar {

    val androidOnboardingConfig: Any = Var.define("androidOnboardingConfig", object : HashMap<String, Map<String, Any>>() {
        init {
            put("registration", object :HashMap<String, Any>() {
                init {
                    put("order", 1)
                    put("suppressed", false)
                }
            })
            put("match_counter", object : HashMap<String, Any>() {
                init {
                    put("order", 3)
                    put("suppressed", false)
                }
            })
        }
    });
}