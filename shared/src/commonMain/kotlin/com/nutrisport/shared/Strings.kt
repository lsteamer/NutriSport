package com.nutrisport.shared

interface StringResources {
    val appName: String

    val brandName: String

    val signInToContinue: String
    val authSuccess: String
    val signIntoGoogle: String
    val pleaseWait: String

    val errorUnknown: String
    val errorNetwork: String
    val errorInternet: String
    val errorIdToken: String
    val errorSignInCancelled: String
    val errorUserNotAvailable : String
    val errorUserCreation: String
    val errorWhileSigningOut: String

    val healthyLifestyle: String

    val drawerItemIcon: String
    val textFieldIcon: String

    val menuIcon: String
    val googleLogo: String

    val contentAnimation: String

    val customers : String
    val unknown : String

}

object EnglishStrings : StringResources {
    override val appName = "KMMShoppin"

    override val brandName = "NutriSport"

    override val signInToContinue = "Sign in to Continue"
    override val authSuccess = "Authentication successful!"
    override val signIntoGoogle = "Sign in with Google"
    override val pleaseWait = "Please wait..."

    override val errorUnknown = "Unknown error"
    override val errorNetwork = "Network error"
    override val errorInternet = "Internet connection unavailable"
    override val errorIdToken = "ID token is null"
    override val errorSignInCancelled = "Sign in cancelled"
    override val errorUserNotAvailable = "User not available"
    override val errorUserCreation = "Error while creating a customer : "
    override val errorWhileSigningOut = "Error while singing out : "


    override val healthyLifestyle = "Healthy Lifestyle"

    override val drawerItemIcon = "Drawer item icon"
    override val textFieldIcon = "Text Field icon"

    override val menuIcon = "Menu Icon"
    override val googleLogo = "Google Logo"

    override val contentAnimation = "ContentAnimation"
    override val customers = "Customers"
    override val unknown = "Unknown"
}