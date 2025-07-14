package com.nutrisport.shared

import nutrisport.shared.generated.resources.Res
import nutrisport.shared.generated.resources.address_db
import nutrisport.shared.generated.resources.app_name
import nutrisport.shared.generated.resources.auth_success
import nutrisport.shared.generated.resources.back_arrow_icon
import nutrisport.shared.generated.resources.brand_name
import nutrisport.shared.generated.resources.cart_db
import nutrisport.shared.generated.resources.city_db
import nutrisport.shared.generated.resources.customer
import nutrisport.shared.generated.resources.customer_does_not_exist
import nutrisport.shared.generated.resources.drawer_item_icon
import nutrisport.shared.generated.resources.email_db
import nutrisport.shared.generated.resources.error
import nutrisport.shared.generated.resources.error_internet
import nutrisport.shared.generated.resources.error_network
import nutrisport.shared.generated.resources.error_unknown
import nutrisport.shared.generated.resources.error_while_creating_customer
import nutrisport.shared.generated.resources.error_while_reading_customer_info
import nutrisport.shared.generated.resources.error_while_signing_out
import nutrisport.shared.generated.resources.error_while_updating_a_customer
import nutrisport.shared.generated.resources.first_name_db
import nutrisport.shared.generated.resources.google_logo
import nutrisport.shared.generated.resources.healthy_lifestyle
import nutrisport.shared.generated.resources.id_token_null
import nutrisport.shared.generated.resources.last_name_db
import nutrisport.shared.generated.resources.menu_icon
import nutrisport.shared.generated.resources.my_profile
import nutrisport.shared.generated.resources.phone_number_db
import nutrisport.shared.generated.resources.please_wait
import nutrisport.shared.generated.resources.postal_code_db
import nutrisport.shared.generated.resources.sign_in_cancelled
import nutrisport.shared.generated.resources.sign_in_to_continue
import nutrisport.shared.generated.resources.sign_in_to_google
import nutrisport.shared.generated.resources.successful_update
import nutrisport.shared.generated.resources.unknown
import nutrisport.shared.generated.resources.update
import nutrisport.shared.generated.resources.user_not_available

object Strings {
    val appName = Res.string.app_name

    val brandName = Res.string.brand_name

    val signInToContinue = Res.string.sign_in_to_continue
    val authSuccess = Res.string.auth_success
    val signIntoGoogle = Res.string.sign_in_to_google
    val pleaseWait = Res.string.please_wait
    val successfulUpdate = Res.string.successful_update

    val error = Res.string.error
    val errorUnknown = Res.string.error_unknown
    val errorNetwork = Res.string.error_network
    val errorInternet = Res.string.error_internet
    val errorIdToken = Res.string.id_token_null
    val errorSignInCancelled = Res.string.sign_in_cancelled
    val errorUserNotAvailable = Res.string.user_not_available
    val errorUserCreation = Res.string.error_while_creating_customer
    val errorWhileSigningOut = Res.string.error_while_signing_out
    val errorWhileReadingCustomer = Res.string.error_while_reading_customer_info
    val customerDoesNotExist = Res.string.customer_does_not_exist
    val errorWhileUpdatingCustomer = Res.string.error_while_updating_a_customer

    val firstName = Res.string.first_name_db
    val lastName = Res.string.last_name_db
    val email = Res.string.email_db
    val city = Res.string.city_db
    val postalCode = Res.string.postal_code_db
    val address = Res.string.address_db
    val phoneNumber = Res.string.phone_number_db
    val cart = Res.string.cart_db

    val healthyLifestyle = Res.string.healthy_lifestyle

    val drawerItemIcon = Res.string.drawer_item_icon
    val menuIcon = Res.string.menu_icon
    val googleLogo = Res.string.google_logo
    val backArrowIcon = Res.string.back_arrow_icon


    val customer = Res.string.customer
    val unknown = Res.string.unknown
    val update = Res.string.update

    val myProfile = Res.string.my_profile
}