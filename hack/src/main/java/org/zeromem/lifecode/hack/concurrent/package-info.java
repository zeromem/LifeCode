package org.zeromem.lifecode.hack.concurrent;
/**
 自旋锁(spin lock)会锁住bus
 本地旋转：p端的cache有效，线程始终读取cache中的状态，不占用总线。
 TTAS即通过本地旋转，比TAS更加高效。
 但是一旦有一个线程改变state值，该值在总线上广播，并使所有p的cache无效。
 将引起其他所有线程调用getAndSet()，(风暴式)争用bus。
 可以采用指数后退策略，避免大量争用。
 */