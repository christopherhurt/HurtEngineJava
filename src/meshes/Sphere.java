package meshes;

import renderEngine.Mesh;

public class Sphere extends Mesh {
	
	private static final float X = 0.525731112119133606f;
	private static final float Z = 0.850650808352039932f;
	private static final float N = 0.0f;
	
	private static final int[] indices = {
			 0,4,1 , 0,9,4 , 9,5,4 , 4,5,8 , 4,8,1 ,
			   8,10,1 , 8,3,10 , 5,3,8 , 5,2,3 , 2,7,3 ,
			   7,10,3 , 7,6,10 , 7,11,6 , 11,0,6 , 0,1,6 ,
			   6,1,10 , 9,0,11 , 9,11,2 , 9,2,5 , 7,2,11 
	};
	
	private static final float[] vertices = {
			v 0.000000 -1.000000 0.000000
			v 0.723607 -0.447220 0.525725
			v -0.276388 -0.447220 0.850649
			v -0.894426 -0.447216 0.000000
			v -0.276388 -0.447220 -0.850649
			v 0.723607 -0.447220 -0.525725
			v 0.276388 0.447220 0.850649
			v -0.723607 0.447220 0.525725
			v -0.723607 0.447220 -0.525725
			v 0.276388 0.447220 -0.850649
			v 0.894426 0.447216 0.000000
			v 0.000000 1.000000 0.000000
			v -0.162456 -0.850654 0.499995
			v 0.425323 -0.850654 0.309011
			v 0.262869 -0.525738 0.809012
			v 0.850648 -0.525736 0.000000
			v 0.425323 -0.850654 -0.309011
			v -0.525730 -0.850652 0.000000
			v -0.688189 -0.525736 0.499997
			v -0.162456 -0.850654 -0.499995
			v -0.688189 -0.525736 -0.499997
			v 0.262869 -0.525738 -0.809012
			v 0.951058 0.000000 0.309013
			v 0.951058 -0.000000 -0.309013
			v 0.000000 0.000000 1.000000
			v 0.587786 0.000000 0.809017
			v -0.951058 0.000000 0.309013
			v -0.587786 -0.000000 0.809017
			v -0.587786 0.000000 -0.809017
			v -0.951058 0.000000 -0.309013
			v 0.587786 0.000000 -0.809017
			v 0.000000 0.000000 -1.000000
			v 0.688189 0.525736 0.499997
			v -0.262869 0.525738 0.809012
			v -0.850648 0.525736 0.000000
			v -0.262869 0.525738 -0.809012
			v 0.688189 0.525736 -0.499997
			v 0.162456 0.850654 0.499995
			v 0.525730 0.850652 0.000000
			v -0.425323 0.850654 0.309011
			v -0.425323 0.850654 -0.309011
			v 0.162456 0.850654 -0.499995
			v -0.084442 -0.961939 0.259889
			v 0.597195 -0.674614 0.433883
			v -0.007027 -0.505727 0.862665
			v 0.818272 -0.505726 0.273263
			v 0.597195 -0.674614 -0.433883
			v -0.273266 -0.961939 -0.000000
			v -0.822618 -0.505724 0.259890
			v -0.084442 -0.961939 -0.259889
			v -0.501372 -0.505727 -0.702044
			v 0.512754 -0.505727 -0.693775
			v 0.870464 -0.232458 0.433884
			v 0.959253 0.232455 -0.160620
			v -0.143662 -0.232458 0.961938
			v 0.449184 0.232458 0.862668
			v -0.959253 -0.232455 0.160620
			v -0.681642 0.232458 0.693778
			v -0.449184 -0.232458 -0.862668
			v -0.870464 0.232458 -0.433884
			v 0.681641 -0.232458 -0.693778
			v 0.143662 0.232458 -0.961938
			v 0.501372 0.505727 0.702044
			v -0.512754 0.505727 0.693775
			v -0.818272 0.505726 -0.273263
			v 0.007027 0.505727 -0.862665
			v 0.822618 0.505724 -0.259890
			v 0.228104 0.674614 0.702043
			v 0.273266 0.961938 -0.000000
			v -0.597195 0.674614 0.433883
			v -0.597195 0.674614 -0.433883
			v 0.228104 0.674614 -0.702043
			v -0.228104 -0.674614 0.702043
			v 0.221076 -0.961939 0.160619
			v 0.512754 -0.505727 0.693775
			v 0.818272 -0.505726 -0.273263
			v 0.221076 -0.961939 -0.160619
			v -0.738174 -0.674610 0.000000
			v -0.501372 -0.505727 0.702044
			v -0.228104 -0.674614 -0.702043
			v -0.822618 -0.505724 -0.259890
			v -0.007027 -0.505727 -0.862665
			v 0.959253 0.232455 0.160620
			v 0.870464 -0.232458 -0.433884
			v 0.143662 0.232458 0.961938
			v 0.681641 -0.232458 0.693778
			v -0.870464 0.232458 0.433884
			v -0.449184 -0.232458 0.862668
			v -0.681641 0.232458 -0.693778
			v -0.959253 -0.232455 -0.160620
			v 0.449184 0.232458 -0.862668
			v -0.143662 -0.232458 -0.961938
			v 0.822618 0.505724 0.259890
			v 0.007027 0.505727 0.862665
			v -0.818272 0.505726 0.273263
			v -0.512754 0.505727 -0.693775
			v 0.501372 0.505727 -0.702044
			v 0.084442 0.961939 0.259889
			v 0.738174 0.674610 -0.000000
			v -0.221076 0.961939 0.160619
			v -0.221076 0.961939 -0.160619
			v 0.084442 0.961939 -0.259889
			v 0.361800 0.894429 -0.262863
			v 0.638195 0.723610 -0.262864
			v 0.447211 0.723611 -0.525727
			v -0.138197 0.894430 -0.425320
			v -0.052790 0.723612 -0.688186
			v -0.361804 0.723612 -0.587779
			v -0.447210 0.894429 0.000000
			v -0.670817 0.723611 -0.162457
			v -0.670817 0.723611 0.162457
			v -0.138197 0.894430 0.425320
			v -0.361804 0.723612 0.587779
			v -0.052790 0.723612 0.688185
			v 0.361800 0.894429 0.262863
			v 0.447211 0.723611 0.525727
			v 0.638195 0.723610 0.262864
			v 0.861804 0.276396 -0.425322
			v 0.809018 -0.000000 -0.587783
			v 0.670820 0.276396 -0.688190
			v -0.138199 0.276397 -0.951055
			v -0.309017 -0.000000 -0.951056
			v -0.447216 0.276397 -0.850648
			v -0.947213 0.276396 -0.162458
			v -1.000000 0.000000 0.000000
			v -0.947213 0.276396 0.162458
			v -0.447216 0.276397 0.850648
			v -0.309017 -0.000000 0.951056
			v -0.138199 0.276397 0.951055
			v 0.670820 0.276396 0.688190
			v 0.809018 -0.000000 0.587783
			v 0.861804 0.276396 0.425322
			v 0.309017 0.000000 -0.951056
			v 0.447216 -0.276397 -0.850648
			v 0.138199 -0.276397 -0.951055
			v -0.809018 0.000000 -0.587783
			v -0.670820 -0.276396 -0.688190
			v -0.861804 -0.276396 -0.425322
			v -0.809018 0.000000 0.587783
			v -0.861804 -0.276396 0.425322
			v -0.670820 -0.276396 0.688190
			v 0.309017 0.000000 0.951056
			v 0.138199 -0.276397 0.951055
			v 0.447216 -0.276397 0.850648
			v 1.000000 -0.000000 0.000000
			v 0.947213 -0.276396 0.162458
			v 0.947213 -0.276396 -0.162458
			v 0.361804 -0.723612 -0.587779
			v 0.138197 -0.894430 -0.425320
			v 0.052790 -0.723612 -0.688186
			v -0.447211 -0.723612 -0.525727
			v -0.361800 -0.894429 -0.262863
			v -0.638195 -0.723610 -0.262864
			v -0.638195 -0.723610 0.262864
			v -0.361800 -0.894429 0.262863
			v -0.447211 -0.723612 0.525727
			v 0.670817 -0.723611 -0.162457
			v 0.670817 -0.723611 0.162457
			v 0.447210 -0.894429 0.000000
			v 0.052790 -0.723612 0.688186
			v 0.138197 -0.894430 0.425320
			v 0.361804 -0.723612 0.587779
			v -0.042625 -0.990369 0.131190
			v 0.666729 -0.566291 0.484402
			v -0.143065 -0.481039 0.864865
			v 0.778327 -0.481038 0.403322
			v 0.666729 -0.566291 -0.484402
			v -0.137942 -0.990369 0.000000
			v -0.866748 -0.481035 0.131190
			v -0.042625 -0.990369 -0.131190
			v -0.392606 -0.481039 -0.783786
			v 0.624104 -0.481039 -0.615593
			v 0.804673 -0.343095 0.484402
			v 0.935720 0.343092 -0.081079
			v -0.212038 -0.343095 0.914978
			v 0.366262 0.343095 0.864868
			v -0.935721 -0.343092 0.081079
			v -0.709357 0.343095 0.615594
			v -0.366262 -0.343095 -0.864868
			v -0.804673 0.343095 -0.484402
			v 0.709357 -0.343095 -0.615594
			v 0.212038 0.343095 -0.914978
			v 0.392606 0.481039 0.783786
			v -0.624104 0.481039 0.615592
			v -0.778327 0.481038 -0.403322
			v 0.143065 0.481039 -0.864865
			v 0.866748 0.481036 -0.131190
			v 0.254663 0.566292 0.783785
			v 0.137942 0.990369 0.000000
			v -0.666729 0.566291 0.484402
			v -0.666729 0.566291 -0.484402
			v 0.254663 0.566292 -0.783785
			v -0.197179 -0.770051 0.606864
			v 0.326342 -0.915110 0.237099
			v 0.391583 -0.520748 0.758700
			v 0.842575 -0.520746 -0.137960
			v 0.326342 -0.915110 -0.237099
			v -0.638098 -0.770047 0.000000
			v -0.600565 -0.520747 0.606866
			v -0.197179 -0.770051 -0.606864
			v -0.762750 -0.520746 -0.383639
			v 0.129165 -0.520748 -0.843967
			v 0.964444 0.117358 0.237100
			v 0.919618 -0.117359 -0.375060
			v 0.072530 0.117359 0.990509
			v 0.640886 -0.117359 0.758705
			v -0.919618 0.117359 0.375060
			v -0.523527 -0.117359 0.843971
			v -0.640886 0.117359 -0.758705
			v -0.964444 -0.117358 -0.237100
			v 0.523527 0.117359 -0.843971
			v -0.072530 -0.117359 -0.990509
			v 0.762750 0.520746 0.383639
			v -0.129165 0.520748 0.843967
			v -0.842575 0.520746 0.137960
			v -0.391583 0.520748 -0.758700
			v 0.600565 0.520747 -0.606866
			v 0.124649 0.915110 0.383637
			v 0.638098 0.770047 -0.000000
			v -0.326342 0.915110 0.237099
			v -0.326342 0.915110 -0.237099
			v 0.124649 0.915110 -0.383637
			v 0.265442 0.883388 -0.386217
			v 0.671412 0.632488 -0.386219
			v 0.574794 0.632489 -0.519200
			v -0.285290 0.883389 -0.371797
			v -0.159844 0.632490 -0.757895
			v -0.316171 0.632490 -0.707100
			v -0.441762 0.883388 0.156431
			v -0.770197 0.632488 -0.082186
			v -0.770197 0.632488 0.082186
			v 0.012262 0.883389 0.468479
			v -0.316171 0.632490 0.707100
			v -0.159844 0.632490 0.757895
			v 0.449343 0.883387 0.133104
			v 0.574794 0.632489 0.519199
			v 0.671411 0.632488 0.386219
			v 0.784639 0.406036 -0.468481
			v 0.707107 0.000065 -0.707105
			v 0.637194 0.139892 -0.757900
			v -0.203091 0.406038 -0.891003
			v -0.453990 0.000065 -0.891006
			v -0.523904 0.139892 -0.840210
			v -0.910153 0.406036 -0.082186
			v -0.987688 0.000065 0.156432
			v -0.960984 0.139892 0.238618
			v -0.359421 0.406037 0.840207
			v -0.156434 0.000065 0.987688
			v -0.070019 0.139892 0.987687
			v 0.688021 0.406036 0.601464
			v 0.891007 0.000065 0.453987
			v 0.917711 0.139892 0.371799
			v 0.156434 -0.000065 -0.987688
			v 0.359421 -0.406037 -0.840208
			v 0.203091 -0.406038 -0.891003
			v -0.891007 -0.000065 -0.453987
			v -0.688021 -0.406036 -0.601464
			v -0.784639 -0.406036 -0.468481
			v -0.707107 -0.000065 0.707105
			v -0.784639 -0.406036 0.468481
			v -0.688021 -0.406036 0.601464
			v 0.453991 -0.000065 0.891006
			v 0.203091 -0.406038 0.891003
			v 0.359421 -0.406037 0.840208
			v 0.987688 -0.000065 -0.156432
			v 0.910153 -0.406036 0.082186
			v 0.910153 -0.406036 -0.082186
			v 0.316171 -0.632490 -0.707101
			v -0.012262 -0.883389 -0.468479
			v -0.055469 -0.796973 -0.601461
			v -0.574794 -0.632489 -0.519200
			v -0.449343 -0.883387 -0.133104
			v -0.589169 -0.796971 -0.133104
			v -0.671411 -0.632488 0.386219
			v -0.265442 -0.883389 0.386217
			v -0.308651 -0.796973 0.519199
			v 0.554883 -0.796972 -0.238617
			v 0.554883 -0.796973 0.238617
			v 0.441762 -0.883388 0.156431
			v 0.159844 -0.632490 0.757895
			v 0.285290 -0.883389 0.371797
			v 0.398411 -0.796973 0.453984
			v -0.124649 -0.915110 0.383637
			v 0.516231 -0.770050 0.375059
			v 0.129165 -0.520748 0.843966
			v 0.842575 -0.520746 0.137960
			v 0.516231 -0.770051 -0.375059
			v -0.403383 -0.915109 0.000000
			v -0.762750 -0.520746 0.383638
			v -0.124649 -0.915110 -0.383637
			v -0.600565 -0.520747 -0.606865
			v 0.391583 -0.520748 -0.758700
			v 0.919618 -0.117359 0.375060
			v 0.964444 0.117358 -0.237100
			v -0.072530 -0.117359 0.990509
			v 0.523527 0.117359 0.843971
			v -0.964444 -0.117358 0.237100
			v -0.640886 0.117359 0.758705
			v -0.523527 -0.117359 -0.843971
			v -0.919618 0.117359 -0.375060
			v 0.640886 -0.117359 -0.758705
			v 0.072530 0.117359 -0.990509
			v 0.600565 0.520747 0.606865
			v -0.391583 0.520748 0.758700
			v -0.842575 0.520746 -0.137960
			v -0.129165 0.520748 -0.843967
			v 0.762750 0.520746 -0.383639
			v 0.197179 0.770051 0.606864
			v 0.403383 0.915109 -0.000000
			v -0.516231 0.770051 0.375059
			v -0.516231 0.770050 -0.375059
			v 0.197179 0.770051 -0.606864
			v -0.254663 -0.566292 0.783785
			v 0.111597 -0.990369 0.081079
			v 0.624104 -0.481039 0.615593
			v 0.778327 -0.481038 -0.403322
			v 0.111597 -0.990369 -0.081079
			v -0.824122 -0.566288 0.000000
			v -0.392606 -0.481039 0.783786
			v -0.254663 -0.566292 -0.783785
			v -0.866748 -0.481035 -0.131190
			v -0.143065 -0.481039 -0.864865
			v 0.935720 0.343092 0.081079
			v 0.804673 -0.343095 -0.484402
			v 0.212038 0.343095 0.914978
			v 0.709357 -0.343095 0.615594
			v -0.804673 0.343095 0.484402
			v -0.366262 -0.343095 0.864868
			v -0.709357 0.343095 -0.615594
			v -0.935720 -0.343092 -0.081079
			v 0.366262 0.343095 -0.864868
			v -0.212038 -0.343095 -0.914978
			v 0.866748 0.481035 0.131190
			v 0.143065 0.481039 0.864866
			v -0.778327 0.481038 0.403322
			v -0.624104 0.481039 -0.615592
			v 0.392606 0.481039 -0.783786
			v 0.042625 0.990369 0.131190
			v 0.824122 0.566288 -0.000000
			v -0.111597 0.990369 0.081079
			v -0.111597 0.990369 -0.081079
			v 0.042625 0.990369 -0.131190
			v 0.449343 0.883387 -0.133104
			v 0.589169 0.796971 -0.133104
			v 0.308650 0.796973 -0.519199
			v 0.012262 0.883389 -0.468479
			v 0.055469 0.796973 -0.601461
			v -0.398411 0.796973 -0.453984
			v -0.441762 0.883388 -0.156431
			v -0.554883 0.796972 -0.238617
			v -0.554883 0.796973 0.238617
			v -0.285290 0.883389 0.371797
			v -0.398411 0.796973 0.453984
			v 0.055469 0.796973 0.601461
			v 0.265442 0.883388 0.386217
			v 0.308651 0.796973 0.519199
			v 0.589169 0.796971 0.133104
			v 0.917711 0.139892 -0.371799
			v 0.891007 0.000065 -0.453987
			v 0.688021 0.406036 -0.601464
			v -0.070019 0.139892 -0.987687
			v -0.156434 0.000065 -0.987688
			v -0.359421 0.406037 -0.840208
			v -0.960984 0.139892 -0.238618
			v -0.987688 0.000065 -0.156432
			v -0.910153 0.406036 0.082186
			v -0.523904 0.139892 0.840210
			v -0.453991 0.000065 0.891006
			v -0.203091 0.406038 0.891003
			v 0.637194 0.139892 0.757900
			v 0.707107 0.000065 0.707105
			v 0.784639 0.406036 0.468481
			v 0.453990 -0.000065 -0.891006
			v 0.523904 -0.139892 -0.840210
			v 0.070019 -0.139892 -0.987687
			v -0.707107 -0.000065 -0.707105
			v -0.637194 -0.139892 -0.757900
			v -0.917711 -0.139892 -0.371799
			v -0.891007 -0.000065 0.453987
			v -0.917711 -0.139892 0.371799
			v -0.637194 -0.139892 0.757900
			v 0.156434 -0.000065 0.987688
			v 0.070019 -0.139892 0.987687
			v 0.523904 -0.139892 0.840210
			v 0.987688 -0.000065 0.156432
			v 0.960984 -0.139892 0.238618
			v 0.960984 -0.139892 -0.238618
			v 0.398411 -0.796973 -0.453984
			v 0.285290 -0.883389 -0.371797
			v 0.159844 -0.632490 -0.757895
			v -0.308651 -0.796973 -0.519199
			v -0.265442 -0.883389 -0.386217
			v -0.671411 -0.632488 -0.386219
			v -0.589169 -0.796971 0.133104
			v -0.449343 -0.883387 0.133104
			v -0.574794 -0.632489 0.519200
			v 0.770197 -0.632488 -0.082186
			v 0.770197 -0.632488 0.082186
			v 0.441762 -0.883388 -0.156431
			v -0.055469 -0.796973 0.601461
			v -0.012262 -0.883389 0.468479
			v 0.316171 -0.632490 0.707101
			v 0.441934 -0.621231 0.647637
			v 0.484615 -0.706596 0.516273
			v 0.562239 -0.597864 0.571189
			v 0.209919 -0.732862 0.646051
			v 0.096773 -0.819297 0.563846
			v 0.253135 -0.819297 0.513040
			v -0.119116 -0.597865 0.792578
			v -0.088597 -0.706596 0.702524
			v 0.023148 -0.621232 0.783711
			v 0.337724 -0.938093 -0.081186
			v 0.337724 -0.938093 0.081186
			v 0.223941 -0.974506 0.000000
			v 0.566155 -0.819296 -0.082204
			v 0.679301 -0.732861 -0.000000
			v 0.566155 -0.819296 0.082204
			v 0.716978 -0.597864 -0.358208
			v 0.752507 -0.621230 -0.220167
			v 0.640762 -0.706596 -0.301353
			v -0.479377 -0.621231 0.620432
			v -0.341252 -0.706596 0.620432
			v -0.369493 -0.597864 0.711227
			v -0.549567 -0.732861 0.399282
			v -0.506349 -0.819296 0.266272
			v -0.409710 -0.819297 0.399282
			v -0.790599 -0.597861 0.131630
			v -0.695521 -0.706593 0.132828
			v -0.738204 -0.621229 0.264191
			v -0.738204 -0.621229 -0.264191
			v -0.695521 -0.706593 -0.132828
			v -0.790599 -0.597861 -0.131630
			v -0.549567 -0.732861 -0.399282
			v -0.409710 -0.819297 -0.399282
			v -0.506349 -0.819296 -0.266272
			v -0.369493 -0.597864 -0.711227
			v -0.341252 -0.706596 -0.620432
			v -0.479377 -0.621231 -0.620432
			v 0.023148 -0.621232 -0.783711
			v -0.088597 -0.706596 -0.702524
			v -0.119116 -0.597865 -0.792578
			v 0.209919 -0.732862 -0.646051
			v 0.253135 -0.819297 -0.513040
			v 0.096773 -0.819297 -0.563846
			v 0.562239 -0.597864 -0.571189
			v 0.484615 -0.706596 -0.516273
			v 0.441934 -0.621231 -0.647637
			v 0.918553 -0.257123 -0.301354
			v 0.892173 -0.395248 -0.220168
			v 0.855384 -0.373920 -0.358208
			v 0.985992 -0.139990 -0.082204
			v 0.985992 -0.139990 0.082204
			v 0.959281 -0.279848 0.000000
			v 0.971774 0.235512 -0.000000
			v 0.990091 0.117456 0.081186
			v 0.990091 0.117456 -0.081186
			v 0.570458 -0.257124 0.780469
			v 0.485093 -0.395249 0.780467
			v 0.605010 -0.373921 0.702821
			v 0.382872 -0.139990 0.912330
			v 0.226507 -0.139990 0.963136
			v 0.296436 -0.279849 0.912329
			v 0.300292 0.235515 0.924212
			v 0.228740 0.117458 0.966721
			v 0.383168 0.117458 0.916544
			v -0.565990 -0.257123 0.783715
			v -0.592369 -0.395249 0.702526
			v -0.481466 -0.373921 0.792581
			v -0.749366 -0.139990 0.646056
			v -0.846005 -0.139990 0.513043
			v -0.776076 -0.279848 0.563849
			v -0.786183 0.235515 0.571191
			v -0.848723 0.117458 0.516275
			v -0.753281 0.117458 0.647640
			v -0.920259 -0.257122 -0.296106
			v -0.851196 -0.395248 -0.346283
			v -0.902572 -0.373918 -0.212981
			v -0.846005 -0.139990 -0.513043
			v -0.749366 -0.139990 -0.646056
			v -0.776075 -0.279848 -0.563849
			v -0.786183 0.235515 -0.571191
			v -0.753281 0.117458 -0.647640
			v -0.848723 0.117458 -0.516275
			v -0.002757 -0.257124 -0.966720
			v 0.066306 -0.395250 -0.916541
			v -0.076348 -0.373921 -0.924211
			v 0.226507 -0.139990 -0.963136
			v 0.382872 -0.139990 -0.912330
			v 0.296436 -0.279849 -0.912329
			v 0.300292 0.235515 -0.924212
			v 0.383168 0.117458 -0.916544
			v 0.228740 0.117458 -0.966721
			v 0.851196 0.395248 0.346283
			v 0.920259 0.257122 0.296106
			v 0.902572 0.373918 0.212981
			v 0.776076 0.279848 0.563849
			v 0.749366 0.139990 0.646056
			v 0.846004 0.139990 0.513043
			v 0.481466 0.373921 0.792581
			v 0.565990 0.257123 0.783715
			v 0.592369 0.395249 0.702526
			v -0.066306 0.395250 0.916541
			v 0.002757 0.257124 0.966720
			v 0.076348 0.373921 0.924211
			v -0.296436 0.279849 0.912329
			v -0.382872 0.139990 0.912330
			v -0.226507 0.139990 0.963136
			v -0.605010 0.373921 0.702821
			v -0.570458 0.257124 0.780469
			v -0.485093 0.395249 0.780467
			v -0.892173 0.395248 0.220167
			v -0.918553 0.257123 0.301354
			v -0.855384 0.373920 0.358208
			v -0.959281 0.279848 0.000000
			v -0.985992 0.139990 -0.082204
			v -0.985992 0.139990 0.082204
			v -0.855384 0.373920 -0.358208
			v -0.918553 0.257123 -0.301354
			v -0.892173 0.395248 -0.220168
			v -0.485093 0.395249 -0.780467
			v -0.570458 0.257124 -0.780469
			v -0.605010 0.373920 -0.702821
			v -0.296436 0.279849 -0.912329
			v -0.226507 0.139990 -0.963136
			v -0.382872 0.139990 -0.912330
			v 0.076348 0.373921 -0.924211
			v 0.002757 0.257124 -0.966720
			v -0.066306 0.395250 -0.916541
			v 0.592369 0.395249 -0.702527
			v 0.565990 0.257123 -0.783715
			v 0.481466 0.373921 -0.792581
			v 0.776076 0.279848 -0.563849
			v 0.846005 0.139990 -0.513043
			v 0.749366 0.139990 -0.646056
			v 0.902572 0.373918 -0.212981
			v 0.920259 0.257122 -0.296106
			v 0.851196 0.395248 -0.346283
			v 0.695522 0.706593 0.132828
			v 0.738204 0.621229 0.264191
			v 0.790599 0.597861 0.131630
			v 0.506349 0.819296 0.266272
			v 0.409710 0.819296 0.399282
			v 0.549567 0.732861 0.399282
			v 0.181172 0.974506 0.131629
			v 0.225503 0.938093 0.264190
			v 0.320945 0.938093 0.132827
			v 0.088597 0.706596 0.702524
			v -0.023148 0.621232 0.783711
			v 0.119116 0.597865 0.792578
			v -0.096773 0.819297 0.563846
			v -0.253135 0.819297 0.513040
			v -0.209919 0.732862 0.646051
			v -0.069202 0.974506 0.212980
			v -0.181577 0.938094 0.296104
			v -0.027151 0.938094 0.346281
			v -0.640762 0.706596 0.301353
			v -0.752507 0.621230 0.220167
			v -0.716978 0.597864 0.358207
			v -0.566155 0.819296 0.082204
			v -0.566155 0.819296 -0.082204
			v -0.679301 0.732861 -0.000000
			v -0.223941 0.974506 0.000000
			v -0.337724 0.938093 -0.081186
			v -0.337724 0.938093 0.081186
			v -0.484615 0.706596 -0.516273
			v -0.441934 0.621231 -0.647637
			v -0.562239 0.597864 -0.571189
			v -0.253135 0.819297 -0.513040
			v -0.096773 0.819297 -0.563846
			v -0.209919 0.732862 -0.646051
			v -0.069202 0.974506 -0.212980
			v -0.027151 0.938094 -0.346281
			v -0.181577 0.938094 -0.296104
			v 0.341252 0.706596 -0.620432
			v 0.479377 0.621231 -0.620432
			v 0.369493 0.597864 -0.711227
			v 0.409709 0.819296 -0.399282
			v 0.506349 0.819296 -0.266272
			v 0.549567 0.732861 -0.399283
			v 0.181172 0.974506 -0.131629
			v 0.320945 0.938093 -0.132827
			v 0.225503 0.938093 -0.264190
			v 0.695522 0.706593 -0.132828
			v 0.790599 0.597861 -0.131630
			v 0.738204 0.621229 -0.264191
			v 0.088597 0.706596 -0.702524
			v 0.119116 0.597865 -0.792578
			v -0.023148 0.621232 -0.783711
			v -0.640762 0.706596 -0.301353
			v -0.716978 0.597864 -0.358207
			v -0.752507 0.621230 -0.220167
			v -0.484615 0.706596 0.516273
			v -0.562239 0.597864 0.571189
			v -0.441934 0.621231 0.647637
			v 0.341252 0.706596 0.620432
			v 0.369493 0.597864 0.711227
			v 0.479377 0.621231 0.620432
			v 0.848723 -0.117458 -0.516275
			v 0.786183 -0.235514 -0.571191
			v 0.753281 -0.117458 -0.647641
			v -0.228740 -0.117458 -0.966721
			v -0.300292 -0.235515 -0.924212
			v -0.383168 -0.117458 -0.916544
			v -0.990091 -0.117456 -0.081186
			v -0.971774 -0.235512 -0.000000
			v -0.990091 -0.117456 0.081186
			v -0.383168 -0.117458 0.916544
			v -0.300292 -0.235515 0.924212
			v -0.228740 -0.117458 0.966720
			v 0.753281 -0.117458 0.647640
			v 0.786183 -0.235514 0.571191
			v 0.848723 -0.117458 0.516275
			v 0.570458 -0.257124 -0.780469
			v 0.605010 -0.373921 -0.702821
			v 0.485093 -0.395250 -0.780467
			v -0.565990 -0.257123 -0.783715
			v -0.481466 -0.373920 -0.792581
			v -0.592369 -0.395249 -0.702526
			v -0.920259 -0.257122 0.296106
			v -0.902572 -0.373918 0.212981
			v -0.851196 -0.395248 0.346282
			v -0.002757 -0.257124 0.966720
			v -0.076348 -0.373921 0.924211
			v 0.066306 -0.395250 0.916541
			v 0.918553 -0.257123 0.301354
			v 0.855384 -0.373920 0.358208
			v 0.892173 -0.395248 0.220167
			v 0.181577 -0.938094 -0.296104
			v 0.069203 -0.974506 -0.212980
			v 0.027151 -0.938094 -0.346281
			v -0.225503 -0.938093 -0.264190
			v -0.181172 -0.974506 -0.131629
			v -0.320945 -0.938093 -0.132827
			v -0.320945 -0.938093 0.132827
			v -0.181172 -0.974506 0.131629
			v -0.225503 -0.938093 0.264190
			v 0.752507 -0.621230 0.220167
			v 0.716978 -0.597864 0.358208
			v 0.640762 -0.706596 0.301353
			v 0.027151 -0.938094 0.346281
			v 0.069202 -0.974506 0.212980
			v 0.181577 -0.938094 0.296104
	};
	
	private static final float[] texCoords = {
			0, 1,
			0, 0,
			1, 1,
			1, 0,
			
			0, 1,
			0, 0,
			1, 1,
			1, 0,
			
			0, 1,
			0, 0,
			1, 1,
			1, 0
	};
	
	public Sphere(){
		super(indices, vertices, texCoords, null, null);
	}
	
}
