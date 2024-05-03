package com.example.medisim.presentation.authScreens.signUp

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.medisim.R
import com.example.medisim.data.remote.dto.auth.SignUpBody
import com.example.medisim.data.remote.dto.main.ChronicDisease
import com.example.medisim.domain.SharedPreferences
import com.example.medisim.domain.repository.ApiServicesRepository
import com.example.medisim.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    private val repo : ApiServicesRepository,
    private val pref: SharedPreferences
):ViewModel() {

    private var _state by mutableStateOf(
       SignUpScreenState()
    )

    val state: State<SignUpScreenState>
        get() = derivedStateOf { _state }


    private val _chronicDiseasesList = MutableStateFlow(emptyList<ChronicDisease>())
    val chronicDiseasesList: StateFlow<List<ChronicDisease>> = _chronicDiseasesList

    private val selectedChronicDiseasesIds: MutableList<Int> = mutableListOf()

    init {
        getAllChronics()
    }

    private fun getAllChronics() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getChronicDiseases().collect{
                _chronicDiseasesList.value = it
            }

        }

    }




    // when user need to make password is visible
    fun onIconShowPassword(){
        val newShowPassword = _state.showPassword.not()
        _state = _state.copy(
            showPassword = newShowPassword
        )
    }

    // when user need to make confirm password is visible
    fun onIconShowConfirmPassword(){
        val newShowConfirmPassword = _state.showConfirmPassword.not()
        _state = _state.copy(
            showConfirmPassword = newShowConfirmPassword
        )
    }

    // when user write in email editText update state
    fun onUserNameChange(userName:String){
        _state = _state.copy(
            userName = userName,
            isErrorEmail  = false,
            userNameErrorMessage = "",
            errorMessage = ""
        )
    }


    // when user write in email editText update state
    fun onEmailChange(email:String){
        _state = _state.copy(
            email = email,
            isErrorEmail  = false,
            emailErrorMessage = "",
            errorMessage = ""
        )
    }

    // when user write in password editText update state
    fun onPasswordChange(password:String){
        _state = _state.copy(
            password = password,
            isErrorPassword = false,
            passwordErrorMessage = "",
            errorMessage = ""
        )

    }


    // when user write in password editText update state
    fun onConfirmPasswordChange(password:String){
        _state = _state.copy(
            confirmPassword = password,
            isErrorConfirmPassword  = false,
            confirmPasswordErrorMessage = "",
            errorMessage = ""
        )

    }



    fun onNextToSecondScreen(navController: NavHostController, context: Context){
        if (_state.userName.isEmpty()){
            _state=_state.copy(
                isErrorUserName = true,
                userNameErrorMessage = context.getString(R.string.please_enter_your_name)
            )

        }
        if (_state.email.isEmpty()){
            _state=_state.copy(
                isErrorEmail = true,
                emailErrorMessage = context.getString(R.string.please_enter_your_email)
            )

        }
        if (isValidEmail(_state.email).not()){
            _state=_state.copy(
                isErrorEmail = true,
                emailErrorMessage = context.getString(R.string.please_enter_valid_email)
            )

        }
        if (_state.password.isEmpty()){
            _state=_state.copy(
                isErrorPassword = true,
                passwordErrorMessage = context.getString(R.string.please_enter_your_password)
            )

        }

        if (_state.confirmPassword.isEmpty()){
            _state=_state.copy(
                isErrorConfirmPassword = true,
                confirmPasswordErrorMessage = context.getString(R.string.please_enter_confirm_password)
            )

        }
        if (_state.userName.isNotEmpty() &&
            _state.password.isNotEmpty() &&
            _state.confirmPassword.isNotEmpty() &&
            _state.email.isNotEmpty() &&
            isValidEmail(_state.email)
            ){
            navController.navigate(Screens.UserInfo.route)
        }



    }
    private fun isValidEmail(email: String): Boolean {
        // Regular expression pattern to validate the email format
        val pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

        // Check if the provided email matches the pattern
        return email.matches(Regex(pattern))
    }




    ////////////////////////////////////////////////////////////////////
    // second screen in sign up " user information


    fun onHeightChange(height: String,context:Context) {
        _state = if (height.toDoubleOrNull() != null || height.isEmpty()) {
            _state.copy(
                height = height,
                isErrorHeight = false,
                heightErrorMessage = "",
                errorMessage = ""
            )
        } else {
            _state.copy(
                isErrorHeight = true,
                heightErrorMessage = context.getString(R.string.please_enter_a_valid_height)
            )
        }
    }
        fun onWeightChange(weight: String,context:Context) {
        _state = if (weight.toDoubleOrNull() != null || weight.isEmpty()) {
            _state.copy(
                weight = weight,
                isErrorWeight = false,
                weightErrorMessage = "",
                errorMessage = ""
            )
        } else {
            _state.copy(
                isErrorWeight = true,
                weightErrorMessage = context.getString(R.string.please_enter_a_valid_weight)
            )
        }
    }
        fun onAgeChange(age: String,context:Context) {
        _state = if (age.toIntOrNull() != null || age.isEmpty()) {
            _state.copy(
                age = age,
                isErrorAge = false,
                ageErrorMessage = "",
                errorMessage = ""
            )
        } else {
            _state.copy(
                isErrorAge = true,
                ageErrorMessage = context.getString(R.string.please_enter_a_valid_age),
            )
        }
    }



    // when user need to make password is visible
    fun onGenderSelectMale(){
        val newMaleValue = _state.male.not()
        _state = _state.copy(
            male  = newMaleValue,
            female = newMaleValue.not(),
            genderErrorMessage = "",
            errorMessage = ""
        )
    }


    // when user need to make password is visible
    fun onGenderSelectFemale(){
        val newFemaleValue = _state.female.not()
        _state = _state.copy(
            male  = newFemaleValue.not(),
            female = newFemaleValue,
            genderErrorMessage = "",
            errorMessage = ""
        )
    }

    fun onNextToLastScreen(navController: NavHostController, context: Context){
        if (_state.height.isEmpty()){
            _state=_state.copy(
                isErrorHeight = true,
                heightErrorMessage = context.getString(R.string.please_enter_your_height)
            )
        }
        if (_state.weight.isEmpty()){
            _state=_state.copy(
                isErrorWeight = true,
                weightErrorMessage = context.getString(R.string.please_enter_your_weight)
            )
        }
        if (_state.age.isEmpty()){
            _state=_state.copy(
                isErrorAge = true,
                ageErrorMessage = context.getString(R.string.please_enter_your_age)
            )
        }
        if ( _state.male == _state.female){
            _state=_state.copy(
                genderErrorMessage = context.getString(R.string.please_select_your_gender)
            )
        }

        if (_state.height.isNotEmpty()&&
            _state.weight.isNotEmpty()&&
            _state.age.isNotEmpty()&&
            _state.male == _state.female.not()
            ){
            navController.navigate(Screens.UserChronic.route)

        }
    }

    /////////////////////////////////////////////////////////


    fun onSelectChronic(chronicDisease: ChronicDisease){
        chronicDisease.isSelected = chronicDisease.isSelected.not()
        _state = _state.copy(
            errorMessage = ""
        )
        if (chronicDisease.isSelected){
            selectedChronicDiseasesIds.add(chronicDisease.id)
        }else{
            selectedChronicDiseasesIds.remove(chronicDisease.id)
        }

    }

    fun onSignUpClick(navController: NavHostController) {
        viewModelScope.launch(Dispatchers.IO){
            val signUpResponse = repo.signUp(
                SignUpBody(
                    _state.userName,
                    _state.email,
                    _state.password,
                    _state.height.toDouble(),
                    _state.weight.toDouble(),
                    _state.age.toInt(),
                    _state.male,
                    selectedChronicDiseasesIds,
                )
            )

            if (signUpResponse.token.isNotEmpty()){
                pref.setSharedPreferences("token",signUpResponse.token)
                pref.setSharedPreferences("userName",_state.userName)
                pref.setSharedPreferences("email",_state.email)
                pref.setSharedPreferences("rememberMe",signUpResponse.token)

                _state = _state.copy(
                    errorMessage = ""
                )

                // go to home screen after save token in sharedPreferences
                withContext(Dispatchers.Main){
                    navController.navigate(Screens.RegistrationSuccessfully.route){
                        popUpTo(Screens.UserChronic.route) {
                            inclusive = true
                        }
                    }
                }



            }else{
                _state = _state.copy(
                    errorMessage = "some error happened please try again*"
                )
            }
        }
    }






}