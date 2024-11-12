package com.matrosov.mapper;

public interface Mapper<B, A> {
    A map(B entity);
}
