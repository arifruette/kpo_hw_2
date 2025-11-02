package domain.file

class ImportConfig private constructor(
    val categoriesFile: String?,
    val bankAccountsFile: String?,
    val operationsFile: String?
) {
    class Builder {
        private var categoriesFile: String? = null
        private var bankAccountsFile: String? = null
        private var operationsFile: String? = null

        fun categoriesFile(file: String?) = apply { this.categoriesFile = file }
        fun bankAccountsFile(file: String?) = apply { this.bankAccountsFile = file }
        fun operationsFile(file: String?) = apply { this.operationsFile = file }
        fun build() = ImportConfig(categoriesFile, bankAccountsFile, operationsFile)
    }

    val importAnything: Boolean get() = categoriesFile != null || bankAccountsFile != null || operationsFile != null
}