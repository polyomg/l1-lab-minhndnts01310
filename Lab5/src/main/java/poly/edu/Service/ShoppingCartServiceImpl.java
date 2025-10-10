package poly.edu.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import poly.edu.database.DB;
import poly.edu.entity.Item;

import java.util.*;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
    Map<Integer, Item
    > map = new HashMap<>();

    @Override
    public Item add(Integer id) {
        // TODO Auto-generated method stub
        if(map.containsKey(id)) {
            Item item = map.get(id);
            item.setQty(item.getQty() + 1);
            return item;
        } else {
            Item item = DB.items.get(id);
            if(item != null) {
                Item newItem = new Item(item.getId(), item.getName(), item.getPrice(), 1);
                map.put(id, newItem);
                return newItem;
            }
        }
        return null;
    }

    @Override
    public void remove(Integer id) {
        // TODO Auto-generated method stub
        map.remove(id);
    }

    @Override
    public Item update(Integer id, int qty) {
        // TODO Auto-generated method stub
        if(map.containsKey(id)) {
            if(qty <= 0) {
                remove(id);
                return null;
            } else {
                Item item = map.get(id);
                item.setQty(qty);
                return item;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        map.clear();
    }

    @Override
    public Collection<Item> getItems() {
        // TODO Auto-generated method stub
        return map.values();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return map.values().stream()
                .mapToInt(Item::getQty)
                .sum();
    }

    @Override
    public double getAmount() {
        // TODO Auto-generated method stub
        return map.values().stream()
                .mapToDouble(item -> item.getPrice() * item.getQty())
                .sum();
    }
}
