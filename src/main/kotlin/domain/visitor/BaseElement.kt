package domain.visitor

interface BaseElement {
    fun acceptVisitor(visitor: Visitor): String
}