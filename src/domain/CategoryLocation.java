package domain;

public class CategoryLocation {

	private Long idCategory;
	private Long idLocal;
	private String strCategoryName;
	private String strLocalName;
	private String strLatitudLocal;
	private String strLongitudLocal;
	
	public CategoryLocation()
	{
		
	}
	public CategoryLocation(Long idCat,Long idLoc,String CatName,String LocalName,String Latit,String Longi)
	{
		this.idCategory=idCat;
		this.idLocal=idLoc;
		this.strCategoryName=CatName;
		this.strLocalName=LocalName;
		this.strLatitudLocal=Latit;
		this.strLongitudLocal=Longi;
	}
	
	public Long getIdCategory()
	{
		return idCategory;
	}
	public Long getIdLocal()
	{
		return idLocal;
	}
	public String getCategoryName()
	{
		return strCategoryName;
	}
	public String getLocalName()
	{
		return this.strLocalName;
	}
	public String getLatitudLocal()
	{
		return strLatitudLocal;
	}
	public String getLongitudLocal()
	{
		return strLongitudLocal;
	}
	
	
	public void setIdCategory(Long idCat)
	{
		this.idCategory=idCat;
		
	}
	public void setIdLocal(Long idLoc)
	{
		this.idLocal=idLoc;
	}
	public void setCategoryName(String CatName)
	{
		this.strCategoryName=CatName;
	}
	public void setLocalName(String LocName)
	{
		this.strLocalName=LocName;
	}
	public void setLatitudLocal(String LatLoc)
	{
		this.strLatitudLocal=LatLoc;
	}
	public void setLongLocal(String LongLoc)
	{
		this.strLongitudLocal=LongLoc;
	}
}
