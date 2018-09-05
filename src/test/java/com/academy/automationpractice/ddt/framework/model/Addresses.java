package com.academy.automationpractice.ddt.framework.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Addresses extends ForwardingSet<AddressData> {
    private Set<AddressData> delegate;

    public Addresses() {
        this.delegate = new HashSet<>();
    }

    public Addresses(Addresses addresses) {
        this.delegate = new HashSet<>(addresses.delegate);
    }

    @Override
    protected Set<AddressData> delegate() {
        return delegate;
    }

    public Addresses withAdded(AddressData address) {
        Addresses addresses = new Addresses(this);
        addresses.add(address);
        return addresses;
    }

    public Addresses without(AddressData address) {
        Addresses addresses = new Addresses(this);
        addresses.remove(address);
        return addresses;
    }
}
