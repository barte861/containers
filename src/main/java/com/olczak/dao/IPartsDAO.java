package com.olczak.dao;

import java.io.IOException;

import com.olczak.model.Part;
import com.olczak.model.PartList;

public interface IPartsDAO {
	public abstract void addPart(Part p) throws IOException;

	public abstract Part getPart(int id);
	
	public abstract void removePart(int id) throws IOException;
	
	public abstract void modifyPart(int id, Part p) throws IOException;

	public abstract PartList getAllParts();
}
