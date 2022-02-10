package vanrrtech.app.prodiaappsample.UtilServices.LoginHandler

import vanrrtech.app.prodiaappsample.UtilServices.shared_preference.SharedPreferenceService

class LoginHandlerService(val sharedPreferenceService: SharedPreferenceService) {

    companion object {
        val USER_NAME_SP_KEY = "user-name-sp-storage"
        val USER_PASSWORD_SP_KEY = "user-pass-sp-storage"
        val USER_IMG_SP_KEY = "user-img-sp-storage"
    }

    val mImageList = ArrayList<String>()

    init {
        mImageList.add("https://awsimages.detik.net.id/visual/2021/12/30/northkorea-kimjongun_169.jpeg?w=360&q=90")
        mImageList.add("https://www.ranahriau.com/foto_berita/28908511.jpg")
        mImageList.add("https://lastfm.freetls.fastly.net/i/u/770x0/7798032e130cb29688b2f57bc6748abc.jpg")
        mImageList.add("http://c.files.bbci.co.uk/11AE3/production/_110791427_jkw01.png")
        mImageList.add("https://www.smamcileungsi.sch.id/wp-content/uploads/2021/09/ahmad-dahlan.png")
    }

    fun randomImageGenerator() : String {
        val index = (Math.random() * mImageList.size).toInt()
        return mImageList.get(index)
    }

    fun login (userName : String, userPassword: String): Boolean {
        if(!validateUserCredentialInfo(userName, userPassword)){ return false}
        sharedPreferenceService.insertString(USER_NAME_SP_KEY, userName)
        sharedPreferenceService.insertString(USER_PASSWORD_SP_KEY, userPassword)
        sharedPreferenceService.insertString(USER_IMG_SP_KEY, randomImageGenerator())
        return true
    }

    fun logout(){
        sharedPreferenceService.delete(USER_NAME_SP_KEY)
        sharedPreferenceService.delete(USER_PASSWORD_SP_KEY)
        sharedPreferenceService.delete(USER_IMG_SP_KEY)
    }

    fun obtainUserCredential(): LoginCredential {
        return LoginCredential(
            sharedPreferenceService.getString(USER_NAME_SP_KEY),
            sharedPreferenceService.getString(USER_PASSWORD_SP_KEY),
            sharedPreferenceService.getString(USER_IMG_SP_KEY)
        )
    }

    private fun validateUserCredentialInfo(userName : String, userPassword: String): Boolean {
        return (userName.isNotEmpty() && userPassword.isNotEmpty())
    }

    data class LoginCredential(val userName : String,
                               val userPassword : String,
                               val userImg : String,
    )
}