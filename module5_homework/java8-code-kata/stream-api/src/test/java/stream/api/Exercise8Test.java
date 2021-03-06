package stream.api;

import common.test.tool.annotation.Difficult;
import common.test.tool.dataset.ClassicOnlineStore;
import common.test.tool.entity.Customer;
import common.test.tool.entity.Item;
import common.test.tool.entity.Shop;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class Exercise8Test extends ClassicOnlineStore {

    @Difficult @Test
    public void itemsNotOnSale() {
        Stream<Customer> customerStream = this.mall.getCustomerList().stream();
        Stream<Shop> shopStream = this.mall.getShopList().stream();

        /**
         * Create a set of item names that are in {@link Customer.wantToBuy} but not on sale in any shop.
         */
        List<String> itemListOnSale = shopStream
            .flatMap(shop -> shop.getItemList().stream())
            .map(Item::getName)
            .distinct()
            .collect(Collectors.toList());

        Set<String> itemSetNotOnSale = customerStream
            .flatMap(customer -> customer.getWantToBuy().stream())
            .filter(item -> !itemListOnSale.contains(item.getName()))
            .map(Item::getName)
            .collect(Collectors.toSet());

        assertThat(itemSetNotOnSale, hasSize(3));
        assertThat(itemSetNotOnSale, hasItems("bag", "pants", "coat"));
    }

    @Difficult @Test
    public void havingEnoughMoney() {
        Stream<Customer> customerStream = this.mall.getCustomerList().stream();
        Stream<Shop> shopStream = this.mall.getShopList().stream();

        /**
         * Create a customer's name list including who are having enough money to buy all items they want which is on sale.
         * Items that are not on sale can be counted as 0 money cost.
         * If there is several same items with different prices, customer can choose the cheapest one.
         */
        Comparator<Item> itemPriceComparator = Comparator.comparingInt(Item::getPrice);
        Supplier<TreeSet<Item>> itemPriceComparedSupplier = () -> new TreeSet<>(itemPriceComparator);
        Collector<Item, ?, TreeSet<Item>> itemPriceComparedCollector = Collectors.toCollection(itemPriceComparedSupplier);

        List<Item> onSale = shopStream
            .flatMap(shop -> shop.getItemList().stream())
            .collect(Collectors.collectingAndThen(itemPriceComparedCollector, ArrayList::new));

        Predicate<Customer> havingEnoughMoney = customer -> {
            List<String> wantToBuyItemNames = customer.getWantToBuy().stream()
                .map(Item::getName)
                .collect(Collectors.toList());

            int sum = onSale.stream()
                .filter(item -> wantToBuyItemNames.contains(item.getName()))
                .mapToInt(Item::getPrice)
                .sum();

            return customer.getBudget() >= sum;
        };

        List<String> customerNameList = customerStream
            .filter(havingEnoughMoney)
            .map(Customer::getName)
            .collect(Collectors.toList());

        assertThat(customerNameList, hasSize(7));
        assertThat(customerNameList, hasItems("Joe", "Patrick", "Chris", "Kathy", "Alice", "Andrew", "Amy"));
    }

}
