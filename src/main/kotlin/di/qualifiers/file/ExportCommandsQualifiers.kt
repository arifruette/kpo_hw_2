package di.qualifiers.file

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ExportBankAccountsToCsv

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ExportBankAccountsToJson

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ExportCategoriesToCsv

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ExportCategoriesToJson

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ExportOperationsToCsv

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ExportOperationsToJson