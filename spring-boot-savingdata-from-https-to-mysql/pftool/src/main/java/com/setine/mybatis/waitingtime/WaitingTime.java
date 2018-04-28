package com.setine.mybatis.waitingtime;

//@Alias("WaitingTime") 
public class WaitingTime {
	private String _primary_key;
	private String _date;
	private String _server;
	private String _world;
	private String _host;
	private String _instance;
	private String _key;
	private String _melee;
	private String _healer;
	private String _tank;
	private String _ranged;
	
	
	public String get_primary_key() {
		return _primary_key;
	}
	public void set_primary_key(String _primary_key) {
		this._primary_key = _primary_key;
	}
	public String get_date() {
		return _date;
	}
	public void set_date(String _date) {
		this._date = _date;
	}
	public String get_server() {
		return _server;
	}
	public void set_server(String _server) {
		this._server = _server;
	}
	public String get_world() {
		return _world;
	}
	public void set_world(String _world) {
		this._world = _world;
	}
	public String get_host() {
		return _host;
	}
	public void set_host(String _host) {
		this._host = _host;
	}
	public String get_instance() {
		return _instance;
	}
	public void set_instance(String _instance) {
		this._instance = _instance;
	}
	public String get_key() {
		return _key;
	}
	public void set_key(String _key) {
		this._key = _key;
	}
	public String get_melee() {
		return _melee;
	}
	public void set_melee(String _melee) {
		this._melee = _melee;
	}
	public String get_healer() {
		return _healer;
	}
	public void set_healer(String _healer) {
		this._healer = _healer;
	}
	public String get_tank() {
		return _tank;
	}
	public void set_tank(String _tank) {
		this._tank = _tank;
	}
	public String get_ranged() {
		return _ranged;
	}
	public void set_ranged(String _ranged) {
		this._ranged = _ranged;
	}
	
}
