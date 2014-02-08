package com.llorieruo.projects.dataObjects.facebook;

public class FacebookUser extends FacebookApiErrorResponse
{
	// Public profile
	private String	id, name, first_name, last_name, link, username, gender, locale;
	private Range	age_range;
	// Extended profile
	private String	email;
	
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getFirst_name()
	{
		return first_name;
	}

	public void setFirst_name(String first_name)
	{
		this.first_name = first_name;
	}

	public String getLast_name()
	{
		return last_name;
	}

	public void setLast_name(String last_name)
	{
		this.last_name = last_name;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getLocale()
	{
		return locale;
	}

	public void setLocale(String locale)
	{
		this.locale = locale;
	}

	public Range getAge_range()
	{
		return age_range;
	}

	public void setAge_range(Range age_range)
	{
		this.age_range = age_range;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public class Range
	{
		private String	min, max;

		public String getMin()
		{
			return min;
		}

		public void setMin(String min)
		{
			this.min = min;
		}

		public String getMax()
		{
			return max;
		}

		public void setMax(String max)
		{
			this.max = max;
		}
	}
}
