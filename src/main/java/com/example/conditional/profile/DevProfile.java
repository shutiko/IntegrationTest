package com.example.conditional.profile;

public class DevProfile implements SystemProfile {
    @Override
    public String getProfile() {
        return "Current profile is dev";
    }
}
