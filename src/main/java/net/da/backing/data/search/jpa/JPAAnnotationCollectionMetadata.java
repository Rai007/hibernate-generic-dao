package net.da.backing.data.search.jpa;

import java.io.Serializable;

import net.da.backing.data.search.Metadata;

public class JPAAnnotationCollectionMetadata implements Metadata {

	Metadata classMetadata;
	Class<?> collectionClass;

	public JPAAnnotationCollectionMetadata(Class<?> klass, Class<?> collectionClass) {
		classMetadata = JPAAnnotationMetadata.getMetadata(klass);
		this.collectionClass = collectionClass;
	}

        @Override
	public Class<?> getCollectionClass() {
		return collectionClass;
	}

        @Override
	public String getIdProperty() {
		return classMetadata.getIdProperty();
	}

        @Override
	public Metadata getIdType() {
		return classMetadata.getIdType();
	}

        @Override
	public Serializable getIdValue(Object object) {
		return classMetadata.getIdValue(object);
	}

        @Override
	public Class<?> getJavaClass() {
		return classMetadata.getJavaClass();
	}
	
        @Override
	public String getEntityName() {
		return classMetadata.getEntityName();
	}

        @Override
	public String[] getProperties() {
		return classMetadata.getProperties();
	}

        @Override
	public Metadata getPropertyType(String property) {
		return classMetadata.getPropertyType(property);
	}

        @Override
	public Object getPropertyValue(Object object, String property) {
		return classMetadata.getPropertyValue(object, property);
	}

        @Override
	public boolean isCollection() {
		return true;
	}

        @Override
	public boolean isEmbeddable() {
		return classMetadata.isEmbeddable();
	}

        @Override
	public boolean isEntity() {
		return classMetadata.isEntity();
	}

        @Override
	public boolean isNumeric() {
		return classMetadata.isNumeric();
	}

        @Override
	public boolean isString() {
		return classMetadata.isString();
	}
}
