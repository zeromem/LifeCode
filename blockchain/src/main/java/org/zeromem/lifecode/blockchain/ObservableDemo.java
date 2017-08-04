package org.zeromem.lifecode.blockchain;


import rx.Observable;
import rx.Subscription;

/**
 * Created by zeromem on 2017/7/31.
 */
public class ObservableDemo {
	public static void main(String[] args) {
		String[] input = {"user1", "user2"};
		Observable<String> observable = Observable.from(input);
		observable.doOnCompleted(() -> System.out.println("complete"));
		Subscription sub = observable.subscribe((x) -> System.out.println("hello " + x));

		sub.unsubscribe();
		observable.doOnUnsubscribe(() -> System.out.println("Some one unsubscribe!"));
	}
}
