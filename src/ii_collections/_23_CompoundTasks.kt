package ii_collections

fun Shop.getCustomersWhoOrderedProduct(product: Product): Set<Customer> {
    // Return the set of customers who ordered the specified product
    return this.customers.filter {
        it.orders.filter {
            it.products.contains(product) }.isNotEmpty()
    }.toSet()
}

fun Customer.getMostExpensiveDeliveredProduct(): Product? {
    // Return the most expensive product among all delivered products
    // (use the Order.isDelivered flag)x
    return this.orders.
            filter { it.isDelivered }.
            map { it.products.maxBy { it.price } }.
            sortedBy { it?.price }.first()
}

fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    // Return the number of times the given product was ordered.
    // Note: a customer may order the same product for several times.
    return this.customers.map { it.orders.count { it.products.contains(product) } }.sum()
}
