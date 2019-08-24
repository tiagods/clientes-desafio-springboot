var Clientes = Clientes || {};

Clientes.MaskMoney = (function(){
	function MaskMoney(){
		this.decimal = $('.js-decimal');
		this.plain = $('.js-plain');
	}
	MaskMoney.prototype.enable = function(){
		this.decimal.maskMoney({decimal : ',', thousands: '.'});
		this.plain.maskMoney({ precision: 0, thousands: '.' });
	}
	return MaskMoney;
}());

$(function() {
	var maskMoney = new Clientes.MaskMoney();
	maskMoney.enable();
});