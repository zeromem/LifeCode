pragma solidity ^0.4.0;


contract PropertyOwner {
    address init;
    // Map[id, List[owner]]
    mapping (uint256 => address[]) map;
    function PropertyOwner() {
        init = msg.sender;
    }

    function kill() {
        if (init == msg.sender) {
            selfdestruct(init);
        }
    }

    function firstOwn(uint256 id, address owner) returns (uint finalSize) {
        uint len = size(id);
        finalSize = len;
        require(len == 0);
        map[id].push(owner);
        finalSize++;
    }

    // transfer if from cur to next.
    // return the new size of trans_list of id.
    function transfer(uint256 id, address cur, address next) returns (uint finalSize) {
        uint len = size(id);
        finalSize = len;
        // require:  transaction must start from cur, current owner is cur and next owner is not cur.
        require(msg.sender == cur && len > 0 && map[id][len - 1] == cur && cur != next);
        map[id].push(next);
        finalSize++;
    }

    function getCurrent(uint256 id) constant returns (address) {
        uint len = size(id);
        require(len > 0);
        return map[id][len - 1];
    }

    function getList(uint256 id, uint size) constant returns (address[] ret) {
        uint len = map[id].length;
        if (size >= len) {
            size = len;
        }
        ret = new address[](size);

        for(uint i = 0; i < size; i++) {
            ret[i] = map[id][len - size + i];
        }
        return ret;
    }

    function size(uint256 id) constant returns (uint ret) {
        ret = map[id].length;
    }
}