pragma solidity ^0.4.8;
contract Token {
    mapping (address => uint) public balancesOf;
    address public owner;
    function Token() {
        owner = msg.sender;
        balancesOf[msg.sender] = 100000000;
    }

    function transfer(address _from, address _to, uint _value) {
        require(msg.sender == owner);
        if (balancesOf[_from] < _value) throw; //避免转移出去的代币超过当前的存货
        if (balancesOf[_to] + _value < balancesOf[_to]) throw; //避免自己调用自己，或者递归调用
        balancesOf[_from] -= _value;
        balancesOf[_to] += _value;
    }

    function mint(uint _amount) {
        require(msg.sender == owner);
        balancesOf[owner] += _amount;
    }
}

