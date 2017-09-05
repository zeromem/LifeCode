pragma solidity ^0.4.0;


contract MapArray {
    function MapArray() {

    }
    struct Foo{
        uint x;
    }
    mapping(uint => Foo[]) foo;

    function add(uint id, uint _x) public {
        foo[id].push(Foo(_x));
    }

    function get(uint id, uint index) constant public returns(uint){
        return foo[id][index].x;
    }
}
