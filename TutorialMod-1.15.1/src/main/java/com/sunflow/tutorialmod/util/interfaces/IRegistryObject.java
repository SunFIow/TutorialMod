package com.sunflow.tutorialmod.util.interfaces;

public interface IRegistryObject<T> {
	String getName();

	T get();

	static <T> IRegistryObject<T> create(String name, T obj) {
		return new IRegistryObject<T>() {
			@Override
			public String getName() { return name; }

			@Override
			public T get() { return obj; }
		};
	}
}
