package main.java.cards;

import java.util.ArrayList;

import main.java.cards.type.Color;
import main.java.cards.type.Format;
import main.java.cards.type.Rarity;
import main.java.cards.type.SubType;
import main.java.cards.type.SuperType;

public class Card {
	private String name, originalText, manaCost, printings, artist, borderColor, UUID;
	private ArrayList<Format> legalities;
	private ArrayList<SuperType> supertypes;
	private ArrayList<SubType> subtypes;
	private ArrayList<Color> colorIdentity;
	private Rarity rarity;
	private String power, toughness;
	private Double convertedManaCost;
	private Boolean isReserved;

	public Card() {
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public Boolean getIsReserved() {
		return isReserved;
	}

	public void setIsReserved(Boolean isReserved) {
		this.isReserved = isReserved;
	}

	public String getName() {
		return name;
	}

	public String getOriginalText() {
		return originalText;
	}

	public String getManaCost() {
		return manaCost;
	}

	public String getPrintings() {
		return printings;
	}

	public ArrayList<Format> getLegalities() {
		return legalities;
	}

	public ArrayList<SuperType> getSupertypes() {
		return supertypes;
	}

	public ArrayList<SubType> getSubtypes() {
		return subtypes;
	}

	public ArrayList<Color> getColorIdentity() {
		return colorIdentity;
	}

	public String getPower() {
		return power;
	}

	public String getToughness() {
		return toughness;
	}

	public Double getConvertedManaCost() {
		return convertedManaCost;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}

	public void setManaCost(String manaCost) {
		this.manaCost = manaCost;
	}

	public void setPrintings(String printings) {
		this.printings = printings;
	}

	public void setLegalities(ArrayList<Format> legalities) {
		this.legalities = legalities;
	}

	public void setSupertypes(ArrayList<SuperType> supertypes) {
		this.supertypes = supertypes;
	}

	public void setSubtypes(ArrayList<SubType> subtypes) {
		this.subtypes = subtypes;
	}

	public void setColorIdentity(ArrayList<Color> colorIdentity) {
		this.colorIdentity = colorIdentity;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public void setToughness(String toughness) {
		this.toughness = toughness;
	}

	public void setConvertedManaCost(Double convertedManaCost) {
		this.convertedManaCost = convertedManaCost;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}
}
