package data.file

import domain.facade.BankAccountFacade
import domain.facade.CategoryFacade
import domain.facade.OperationFacade
import domain.file.FileImporter
import domain.models.*
import domain.models.common.toId
import java.time.LocalDateTime
import javax.inject.Inject

class CsvImporter @Inject constructor(
    bankAccountFacade: BankAccountFacade,
    categoryFacade: CategoryFacade,
    operationFacade: OperationFacade
) : FileImporter(bankAccountFacade, categoryFacade, operationFacade) {
    
    override fun parseCategories(content: String): List<Category> {
        val lines = content.trim().lines()
        if (lines.size < 2) return emptyList()
        
        return lines.drop(1).mapNotNull { line ->
            val parts = parseCsvLine(line)
            if (parts.size >= 3) {
                try {
                    Category(parts[0].toId(), CategoryType.valueOf(parts[1]), parts[2])
                } catch (e: Exception) { null }
            } else null
        }
    }
    
    override fun parseBankAccounts(content: String): List<BankAccount> {
        val lines = content.trim().lines()
        if (lines.size < 2) return emptyList()
        
        return lines.drop(1).mapNotNull { line ->
            val parts = parseCsvLine(line)
            if (parts.size >= 3) {
                try {
                    BankAccount(parts[0].toId(), parts[1], parts[2].toDouble())
                } catch (e: Exception) { null }
            } else null
        }
    }
    
    override fun parseOperations(content: String): List<Operation> {
        val lines = content.trim().lines()
        if (lines.size < 2) return emptyList()
        
        return lines.drop(1).mapNotNull { line ->
            val parts = parseCsvLine(line)
            if (parts.size >= 7) {
                try {
                    Operation(
                        parts[0].toId(), OperationType.valueOf(parts[1]), parts[2],
                        parts[3].toDouble(), LocalDateTime.parse(parts[4]),
                        parts[5].takeIf { it.isNotBlank() }, parts[6]
                    )
                } catch (e: Exception) { null }
            } else null
        }
    }
    
    private fun parseCsvLine(line: String): List<String> {
        val result = mutableListOf<String>()
        var current = StringBuilder()
        var inQuotes = false
        for (i in line.indices) {
            when {
                line[i] == '"' -> {
                    if (inQuotes && i + 1 < line.length && line[i + 1] == '"') {
                        current.append('"')
                    } else {
                        inQuotes = !inQuotes
                    }
                }
                line[i] == ',' && !inQuotes -> {
                    result.add(current.toString())
                    current = StringBuilder()
                }
                else -> current.append(line[i])
            }
        }
        result.add(current.toString())
        return result
    }
}