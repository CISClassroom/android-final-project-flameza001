package th.ac.kku.cis.rescue

class model {
    companion object Factory {
        fun create(): model = model()
    }

    var name: String? = null
    var id : String? = null
    var detail : String? = null
    var credit : String? = null

}