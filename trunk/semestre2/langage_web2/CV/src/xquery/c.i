declare namespace cv="http://www.univ-rouen.fr/cv";
for $c in collection("cv-base")/cv:cv
where $c/cv:Civilite/cv:Adresse/cv:Ville/text()[contains(.,"Rouen")]
return <result>{$c}</result>