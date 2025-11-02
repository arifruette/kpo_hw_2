package data.file

import data.adapter.XmlToJsonAdapter
import domain.facade.BankAccountFacade
import domain.facade.CategoryFacade
import domain.facade.OperationFacade
import domain.file.FileImporter
import domain.models.BankAccount
import domain.models.Category
import domain.models.Operation

class XmlImporter(
    bankAccountFacade: BankAccountFacade,
    categoryFacade: CategoryFacade,
    operationFacade: OperationFacade,
    private val xmlAdapter: XmlToJsonAdapter,
    private val jsonImporter: JsonImporter
) : FileImporter(bankAccountFacade, categoryFacade, operationFacade) {
    
    override fun parseCategories(content: String): List<Category> {
        val jsonContent = xmlAdapter.convertXmlToJson(content)
        return jsonImporter.parseCategories(jsonContent)
    }
    
    override fun parseBankAccounts(content: String): List<BankAccount> {
        val jsonContent = xmlAdapter.convertXmlToJson(content)
        return jsonImporter.parseBankAccounts(jsonContent)
    }
    
    override fun parseOperations(content: String): List<Operation> {
        val jsonContent = xmlAdapter.convertXmlToJson(content)
        return jsonImporter.parseOperations(jsonContent)
    }
}